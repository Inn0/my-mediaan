package com.mediaan.mymediaan.view

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mediaan.mymediaan.R
import com.mediaan.mymediaan.repository.ProfileRepository
import com.mediaan.mymediaan.util.SharedPrefsUtil
import com.mediaan.mymediaan.view.common.RippleEffectButton
import com.mediaan.mymediaan.viewModel.MyMediaanScreen
import com.mediaan.mymediaan.viewModel.discoverColleague.DiscoverColleagueViewModel

@Composable
fun DiscoverColleagueScreen(
    drawerState: DrawerState,
    navController: NavController,
    profileRepository: ProfileRepository = ProfileRepository()
) {
    val loggedInUserId = SharedPrefsUtil().getLoggedInUserId(LocalContext.current)
    val viewModel = DiscoverColleagueViewModel(profileRepository, loggedInUserId)
    val uiState by viewModel.uiState.collectAsState()

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
                if (uiState.profiles.isNotEmpty()) {
                    val randomProfile = uiState.profiles.random()
                    navController.navigate("${MyMediaanScreen.Profile}/${randomProfile.id}")
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
            RippleEffectButton(text = stringResource(id = R.string.find_matching_button)) {
                viewModel.findMatchingColleague { matchingProfile ->
                    if (matchingProfile != null) {
                        navController.navigate("${MyMediaanScreen.Profile}/${matchingProfile.id}")
                    } else {
                        Log.e("DiscoverColleagueVM", "No matching colleague")
                    }
                }
            }
        }
    }
}