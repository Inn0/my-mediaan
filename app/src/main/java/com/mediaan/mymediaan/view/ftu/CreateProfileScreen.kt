package com.mediaan.mymediaan.view.ftu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mediaan.mymediaan.model.Office
import com.mediaan.mymediaan.ui.theme.MediaanPrimary
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
        var age by remember { mutableStateOf("") }
        val offices: List<String> = Office.entries.map { it.toString() } // TODO: make accessible from ViewModel
        var isExpanded by remember { mutableStateOf(false) }
        var selectedOffice by remember { mutableStateOf(offices[0]) }
        var firstTruth by remember { mutableStateOf("") }
        var secondTruth by remember { mutableStateOf("") }
        var lie by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .verticalScroll(scrollState)
                .fillMaxSize(),
        ) {
            Text(
                text = "Basic information",
                modifier = Modifier.padding(bottom = 16.dp)
            )
            // TODO: validate min 3 char, max 100 char
            OutlinedTextField(
                value = fullName,
                onValueChange = { fullName = it },
                label = { Text("Full name") },
            )
            // TODO: validate 18-99
            OutlinedTextField(
                value = age,
                onValueChange = { age = it },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                label = { Text("Age") },
            )
            ExposedDropdownMenuBox(
                expanded = isExpanded,
                onExpandedChange = {
                    isExpanded = !isExpanded
                },
                modifier = Modifier.padding(vertical = 8.dp),
            ) {
                TextField(
                    value = selectedOffice,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
                    modifier = Modifier.menuAnchor(),
                )
                ExposedDropdownMenu(
                    expanded = isExpanded,
                    onDismissRequest = { isExpanded = false }
                ) {
                    offices.forEach { item ->
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
                    .padding(bottom = 16.dp)
            )
            // TODO: validate max 280 char
            OutlinedTextField(
                value = firstTruth,
                onValueChange = { firstTruth = it },
                label = { Text("First truth (max 280 char.)") },
            )
            // TODO: validate max 280 char
            OutlinedTextField(
                value = secondTruth,
                onValueChange = { secondTruth = it },
                label = { Text("Second truth (max 280 char.)") },
            )
            // TODO: validate max 280 char
            OutlinedTextField(
                value = lie,
                onValueChange = { lie = it },
                label = { Text("Nice little lie (max 280 char.)") },
            )

            Button(
                onClick = {
                    navController.navigate(MyMediaanScreen.DiscoverColleague.name)
                    viewModel.updateIsOnboardingDone()
                    // TODO: set input values to new profile in profile repository (via ViewModel)
                    // TODO: save created account on root level
                },
                colors = ButtonDefaults.buttonColors(containerColor = MediaanPrimary),
                modifier = Modifier.padding(top = 24.dp)
            ) {
                Text("Ready!")
            }
        }
    }
}