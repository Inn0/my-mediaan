package com.mediaan.mymediaan.view.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mediaan.mymediaan.ui.theme.MediaanPrimary
import com.mediaan.mymediaan.view.MyMediaanAppBar
import com.mediaan.mymediaan.viewModel.MainNavigationViewModel
import com.mediaan.mymediaan.viewModel.MyMediaanScreen

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

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .verticalScroll(scrollState)
                .fillMaxSize()
        ) {
            Text(text = "Create profile here")
            Button(
                onClick = {
                    navController.navigate(MyMediaanScreen.DiscoverColleague.name)
                    viewModel.updateIsOnboardingDone()
                },
                colors = ButtonDefaults.buttonColors(containerColor = MediaanPrimary),
                modifier = Modifier
                    .padding(top = 56.dp)
                    .padding(bottom = 24.dp)
            ) {
                Text("Ready!")
            }
        }
    }
}