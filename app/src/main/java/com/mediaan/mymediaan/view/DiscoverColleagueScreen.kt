package com.mediaan.mymediaan.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.mediaan.mymediaan.R
import com.mediaan.mymediaan.repository.ProfileRepository
import com.mediaan.mymediaan.view.common.RippleEffectButton
import com.mediaan.mymediaan.viewModel.MyMediaanScreen

@Composable
fun DiscoverColleagueScreen(
    drawerState: DrawerState,
    navController: NavController,
    profileRepository: ProfileRepository = ProfileRepository()
) {
    val allProfiles = profileRepository.getAllProfiles()

    Scaffold(
        topBar = {
            MyMediaanAppBar(
                drawerState = drawerState,
                title = stringResource(MyMediaanScreen.DiscoverColleague.title),
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            RippleEffectButton(text = stringResource(id = R.string.discover_colleague)) {
                val randomProfile = allProfiles.random()
                navController.navigate("${MyMediaanScreen.Profile}/${randomProfile.id}")
            }
        }
    }
}