package com.mediaan.mymediaan.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mediaan.mymediaan.repository.ProfileRepository
import com.mediaan.mymediaan.viewModel.MyMediaanScreen

@Composable
fun AllColleaguesScreen(
    drawerState: DrawerState,
    navController: NavController,
    profileRepository: ProfileRepository = ProfileRepository(),
) {
    val profiles = remember { profileRepository.getAllProfiles() }

    Scaffold(topBar = {
        MyMediaanAppBar(
            drawerState = drawerState,
            title = stringResource(MyMediaanScreen.AllColleagues.title),
        )
    }){ padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(profiles) { profile ->
                Text(
                    text = profile.firstName,
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable {
                            navController.navigate("${MyMediaanScreen.Profile}/${profile.id}")
                        }
                )
            }
        }
    }
}
