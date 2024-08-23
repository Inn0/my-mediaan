package com.mediaan.mymediaan.view.ftu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mediaan.mymediaan.R
import com.mediaan.mymediaan.model.TwoTruthsOneLieEntity
import com.mediaan.mymediaan.repository.ProfileRepository
import com.mediaan.mymediaan.ui.theme.MediaanPrimary
import com.mediaan.mymediaan.ui.theme.MyMediaanTheme
import com.mediaan.mymediaan.ui.theme.Typography
import com.mediaan.mymediaan.view.MyMediaanAppBar
import com.mediaan.mymediaan.viewModel.MainNavigationViewModel
import com.mediaan.mymediaan.viewModel.MyMediaanScreen
import com.mediaan.mymediaan.viewModel.ftu.FtuViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateProfileScreen(
    navController: NavController,
    viewModel: MainNavigationViewModel,
    profileRepository: ProfileRepository,
) {
    val ftuViewModel = FtuViewModel(offices = viewModel.offices)
    val ftuUiState by ftuViewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            MyMediaanAppBar(
                drawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
                title = stringResource(MyMediaanScreen.Ftu.title),
                navIcon = Icons.Filled.ArrowBack,
                iconDescr = "Back",
                onNavIconClick = { navController.popBackStack() }
            )
        },
    ) { innerPadding ->
        val scrollState = rememberScrollState()
        var isExpanded by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .verticalScroll(scrollState)
                .fillMaxSize(),
        ) {
            Text(
                text = stringResource(id = R.string.create_profile_screen_heading_section1),
                style = Typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            OutlinedTextField(
                value = ftuUiState.firstName,
                onValueChange = {
                    if (it.length <= ftuViewModel.maxCharName) ftuViewModel.updateFirstName(it)
                },
                label = { Text(stringResource(id = R.string.create_profile_screen_first_name_label)) },
                modifier = Modifier.fillMaxWidth(),
                supportingText = {
                    Text(
                        text = "min ${ftuViewModel.minCharName} char., ${ftuUiState.firstName.length} / ${ftuViewModel.maxCharName}",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End,
                    )
                },
                isError = ftuUiState.firstName.isNotEmpty() && ftuUiState.firstName.length < ftuViewModel.minCharName
            )
            OutlinedTextField(
                value = ftuUiState.nickName,
                onValueChange = {
                    if (it.length <= ftuViewModel.maxCharName) ftuViewModel.updateNickName(it)
                },
                label = { Text(stringResource(id = R.string.create_profile_screen_nickname_label)) },
                modifier = Modifier.fillMaxWidth(),
                supportingText = {
                    Text(
                        text = "min ${ftuViewModel.minCharName} char., ${ftuUiState.nickName.length} / ${ftuViewModel.maxCharName}",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End,
                    )
                },
            )
            OutlinedTextField(
                value = ftuUiState.lastName,
                onValueChange = {
                    if (it.length <= ftuViewModel.maxCharName) ftuViewModel.updateLastName(it)
                },
                label = { Text(stringResource(id = R.string.create_profile_screen_last_name_label)) },
                modifier = Modifier.fillMaxWidth(),
                supportingText = {
                    Text(
                        text = "min ${ftuViewModel.minCharName} char., ${ftuUiState.lastName.length} / ${ftuViewModel.maxCharName}",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End,
                    )
                },
                isError = ftuUiState.lastName.isNotEmpty() && ftuUiState.lastName.length < ftuViewModel.minCharName
            )
            OutlinedTextField(
                value = ftuUiState.age,
                onValueChange = { ftuViewModel.updateAge(it) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                label = { Text(stringResource(id = R.string.create_profile_screen_age_label)) },
                modifier = Modifier.fillMaxWidth(),
                supportingText = {
                    Text(
                        text = "min ${ftuViewModel.minAge} y/o",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End,
                    )
                },
                isError = ftuUiState.age.isNotEmpty() && (ftuUiState.age.toIntOrNull()?.let { it < ftuViewModel.minAge || it > ftuViewModel.maxAge } ?: true)
            )
            ExposedDropdownMenuBox(
                expanded = isExpanded,
                onExpandedChange = {
                    isExpanded = !isExpanded
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
            ) {
                TextField(
                    value = ftuUiState.selectedOffice.toString(),
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(),
                )
                ExposedDropdownMenu(
                    expanded = isExpanded,
                    onDismissRequest = { isExpanded = false },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    viewModel.offices.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(text = item.toString()) },
                            onClick = {
                                ftuViewModel.updateSelectedOffice(item)
                                isExpanded = false
                            }
                        )
                    }
                }
            }

            Text(
                text = stringResource(id = R.string.create_profile_screen_heading_section2),
                modifier = Modifier
                    .padding(top = 24.dp)
                    .padding(bottom = 16.dp),
                style = Typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
            )
            OutlinedTextField(
                value = ftuUiState.firstTruth,
                onValueChange = {
                    if (it.length <= ftuViewModel.maxCharFacts) ftuViewModel.updateFirstTruth(it)
                },
                label = { Text(stringResource(id = R.string.create_profile_screen_truth1_label)) },
                modifier = Modifier.fillMaxWidth(),
                supportingText = {
                    Text(
                        text = "min ${ftuViewModel.minCharFacts} char., ${ftuUiState.firstTruth.length} / ${ftuViewModel.maxCharFacts}",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End,
                    )
                },
                isError = ftuUiState.firstTruth.isNotEmpty() && ftuUiState.firstTruth.length < ftuViewModel.minCharFacts,
            )
            OutlinedTextField(
                value = ftuUiState.secondTruth,
                onValueChange = {
                    if (it.length <= ftuViewModel.maxCharFacts) ftuViewModel.updateSecondTruth(it)
                },
                label = { Text(stringResource(id = R.string.create_profile_screen_truth2_label)) },
                modifier = Modifier.fillMaxWidth(),
                supportingText = {
                    Text(
                        text = "min ${ftuViewModel.minCharFacts} char., ${ftuUiState.secondTruth.length} / ${ftuViewModel.maxCharFacts}",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End,
                    )
                },
                isError = ftuUiState.secondTruth.isNotEmpty() && ftuUiState.secondTruth.length < ftuViewModel.minCharFacts,
            )
            OutlinedTextField(
                value = ftuUiState.lie,
                onValueChange = {
                    if (it.length <= ftuViewModel.maxCharFacts) ftuViewModel.updateLie(it)
                },
                label = { Text(stringResource(id = R.string.create_profile_screen_lie_label)) },
                modifier = Modifier.fillMaxWidth(),
                supportingText = {
                    Text(
                        text = "min ${ftuViewModel.minCharFacts} char., ${ftuUiState.lie.length} / ${ftuViewModel.maxCharFacts}",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End,
                    )
                },
                isError = ftuUiState.lie.isNotEmpty() && ftuUiState.lie.length < ftuViewModel.minCharFacts,
            )

            Button(
                enabled = ftuUiState.isProfileComplete,
                onClick = {
                    val newProfile = viewModel.createNewProfile(
                        id = "me",
                        firstName = ftuUiState.firstName,
                        lastName = ftuUiState.lastName,
                        age = ftuUiState.age.toIntOrNull() ?: 0,
                        nickName = ftuUiState.nickName,
                        office = ftuUiState.selectedOffice,
                        twoTruthsOneLie = listOf(
                            TwoTruthsOneLieEntity(ftuUiState.firstTruth, true),
                            TwoTruthsOneLieEntity(ftuUiState.secondTruth, true),
                            TwoTruthsOneLieEntity(ftuUiState.lie, false)
                        ),
                    )
                    profileRepository.addProfile(newProfile)
                    viewModel.updateIsOnboardingDone()
                    navController.navigate(MyMediaanScreen.DiscoverColleague.name)
                },
                colors = ButtonDefaults.buttonColors(containerColor = MediaanPrimary),
                modifier = Modifier
                    .padding(vertical = 24.dp)
            ) {
                Text(
                    text = if (ftuUiState.isProfileComplete) stringResource(id = R.string.create_profile_screen_ready_button_enabled) else stringResource(
                        id = R.string.create_profile_screen_ready_button_disabled
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun CreateProfileScreenPreview() {
    MyMediaanTheme {
        CreateProfileScreen(
            navController = rememberNavController(),
            viewModel = MainNavigationViewModel(),
            profileRepository = ProfileRepository()
        )
    }
}