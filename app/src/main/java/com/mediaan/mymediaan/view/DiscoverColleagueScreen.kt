package com.mediaan.mymediaan.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mediaan.mymediaan.R
import com.mediaan.mymediaan.model.Profile
import com.mediaan.mymediaan.repository.ProfileRepository
import com.mediaan.mymediaan.view.common.RippleEffectButton
import com.mediaan.mymediaan.viewModel.MyMediaanScreen

@Composable
fun DiscoverColleagueScreen(
    drawerState: DrawerState,
    navController: NavController,
    profileRepository: ProfileRepository = ProfileRepository()
) {
    val profiles = remember { mutableStateOf<List<Profile>>(emptyList()) }

    LaunchedEffect(Unit) {
        profiles.value = profileRepository.getAllProfiles()
    }

    Scaffold(
        topBar = {
            MyMediaanAppBar(
                drawerState = drawerState,
                title = stringResource(MyMediaanScreen.DiscoverColleague.title),
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            RippleEffectButton(text = stringResource(id = R.string.find_random_button)) {
                val randomProfile = profiles.value.random()
                navController.navigate("${MyMediaanScreen.Profile}/${randomProfile.id}")
            }
            Spacer(modifier = Modifier.height(40.dp))
            RippleEffectButton(text = stringResource(id = R.string.find_matching_button)) {
                val randomProfile = profiles.value.random()
                navController.navigate("${MyMediaanScreen.Profile}/${randomProfile.id}")
            }
        }
    }
}