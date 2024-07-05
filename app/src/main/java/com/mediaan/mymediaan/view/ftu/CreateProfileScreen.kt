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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mediaan.mymediaan.ui.theme.MediaanPrimary
import com.mediaan.mymediaan.ui.theme.Typography
import com.mediaan.mymediaan.view.MyMediaanAppBar
import com.mediaan.mymediaan.viewModel.MainNavigationViewModel
import com.mediaan.mymediaan.viewModel.MyMediaanScreen

// TODO: move texts to string file
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateProfileScreen(navController: NavController, viewModel: MainNavigationViewModel) {
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
        var fullName by remember { mutableStateOf("") }
        val minCharName = 3
        val maxCharName = 100
        var age by remember { mutableStateOf("") }
        val minAge = 18
        val maxAge = 122
        var isExpanded by remember { mutableStateOf(false) }
        var selectedOffice by remember { mutableStateOf(viewModel.offices[0]) }
        var firstTruth by remember { mutableStateOf("") }
        var secondTruth by remember { mutableStateOf("") }
        var lie by remember { mutableStateOf("") }
        val minCharFacts = 10
        val maxCharFacts = 280
        var isProfileValid by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .verticalScroll(scrollState)
                .fillMaxSize(),
        ) {
            Text(
                text = "Basic information",
                style = Typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            OutlinedTextField(
                value = fullName,
                onValueChange = {
                    if (it.length <= maxCharName) fullName = it
                },
                label = { Text("Full name") },
                modifier = Modifier.fillMaxWidth(),
                supportingText = {
                    Text(
                        text = "min $minCharName char., ${fullName.length} / $maxCharName",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End,
                    )
                },
                isError = fullName.isNotEmpty() && fullName.length < minCharName
            )
            OutlinedTextField(
                value = age,
                onValueChange = { age = it },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                label = { Text("Age") },
                modifier = Modifier.fillMaxWidth(),
                supportingText = {
                    Text(
                        text = "min $minAge y/o",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End,
                    )
                },
                isError = age.isNotEmpty() && (age.toIntOrNull()?.let { it < minAge || it > maxAge } ?: true)
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
                    value = selectedOffice,
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
                            text = { Text(text = item) },
                            onClick = {
                                selectedOffice = item
                                isExpanded = false
                            }
                        )
                    }
                }
            }

            Text(
                text = "Interesting facts about you",
                modifier = Modifier
                    .padding(top = 24.dp)
                    .padding(bottom = 16.dp),
                style = Typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
            )
            OutlinedTextField(
                value = firstTruth,
                onValueChange = {
                    if (it.length <= maxCharFacts) firstTruth = it
                },
                label = { Text("First truth") },
                modifier = Modifier.fillMaxWidth(),
                supportingText = {
                    Text(
                        text = "min $minCharFacts char., ${firstTruth.length} / $maxCharFacts",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End,
                    )
                },
                isError = firstTruth.isNotEmpty() && firstTruth.length < minCharFacts,
            )
            OutlinedTextField(
                value = secondTruth,
                onValueChange = {
                    if (it.length <= maxCharFacts) secondTruth = it
                },
                label = { Text("Second truth") },
                modifier = Modifier.fillMaxWidth(),
                supportingText = {
                    Text(
                        text = "min $minCharFacts char., ${secondTruth.length} / $maxCharFacts",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End,
                    )
                },
                isError = secondTruth.isNotEmpty() && secondTruth.length < minCharFacts,
            )
            OutlinedTextField(
                value = lie,
                onValueChange = { lie = it },
                label = { Text("Nice little lie") },
                modifier = Modifier.fillMaxWidth(),
                supportingText = {
                    Text(
                        text = "min $minCharFacts char., ${lie.length} / $maxCharFacts",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End,
                    )
                },
                isError = lie.isNotEmpty() && lie.length < minCharFacts,
            )

            Button(
                onClick = {
                    /* TODO: check is form fields are not empty
                    A. if empty show alert, smth like

                    if (!isProfileValid) {
                        Text(
                            text= "Please fill in all fields",
                            color = Color.Red,
                            modifier = Modifier.padding(top = 16.dp)
                        )
                    }

                    B. if not empty, navigate, save data and set onboarding to done
                     */

                    navController.navigate(MyMediaanScreen.DiscoverColleague.name)
                    viewModel.updateIsOnboardingDone()
                    // TODO: set input values to new profile in profile repository (via ViewModel)
                    // TODO: save created account on root level
                },
                colors = ButtonDefaults.buttonColors(containerColor = MediaanPrimary),
                modifier = Modifier
                    .padding(vertical = 24.dp)
            ) {
                Text("Ready!")
            }
        }
    }
}