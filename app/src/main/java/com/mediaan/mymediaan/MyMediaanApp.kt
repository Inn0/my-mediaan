package com.mediaan.mymediaan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mediaan.mymediaan.model.DrawerItem
import com.mediaan.mymediaan.repository.ProfileRepository
import com.mediaan.mymediaan.ui.theme.MyMediaanTheme
import com.mediaan.mymediaan.view.AllColleaguesScreen
import com.mediaan.mymediaan.view.DiscoverColleagueScreen
import com.mediaan.mymediaan.view.ftu.CreateProfileScreen
import com.mediaan.mymediaan.view.ftu.FtuScreen
import com.mediaan.mymediaan.view.profile.ProfileScreen
import com.mediaan.mymediaan.viewModel.MainNavigationViewModel
import com.mediaan.mymediaan.viewModel.MyMediaanScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
private fun DrawerContent(
    viewModel: MainNavigationViewModel,
    items: Array<DrawerItem>,
    onMenuClick: (String) -> Unit
) {
    val mainNavigationUiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        items.forEachIndexed { index, item ->
            NavigationDrawerItem(
                label = { Text(text = item.title) },
                icon = {
                    Icon(
                        imageVector = if (index == mainNavigationUiState.currentSelectedItemIndex) {
                            item.selectedIcon
                        } else item.unselectedIcon,
                        contentDescription = item.title
                    )
                },
                selected = index == mainNavigationUiState.currentSelectedItemIndex,
                onClick = {
                    viewModel.updateSelectedItemIndex(index)
                    onMenuClick(item.route)
                }
            )
        }
    }
}

@Composable
fun MainNavigation(
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    mainNavigationViewModel: MainNavigationViewModel = viewModel(),
    profileRepository: ProfileRepository = ProfileRepository(),
) {
    val loggedInUserId = mainNavigationViewModel.getLoggedInUserId()
    LaunchedEffect(loggedInUserId) {
        val userProfile = profileRepository.getProfileById(loggedInUserId)
        mainNavigationViewModel.saveLoggedInUserProfile(userProfile)
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerContent(
                    viewModel = mainNavigationViewModel,
                    items = mainNavigationViewModel.drawerItems
                ) { route ->
                    coroutineScope.launch {
                        drawerState.close()
                    }
                    navController.navigate(route)
                }
            }
        }
    ) {
        val mainNavigationUiState by mainNavigationViewModel.uiState.collectAsState()
        val startDestination = if (mainNavigationUiState.isOnboardingDone) MyMediaanScreen.DiscoverColleague.name else MyMediaanScreen.Ftu.name

        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface),
        ) {
            composable(route = MyMediaanScreen.Ftu.name) {
                FtuScreen(navController)
            }
            composable(route = MyMediaanScreen.CreateProfile.name) {
                CreateProfileScreen(navController, mainNavigationViewModel, profileRepository)
            }
            composable(route = MyMediaanScreen.DiscoverColleague.name) {
                DiscoverColleagueScreen(drawerState, navController, profileRepository, mainNavigationViewModel)
            }
            composable(route = MyMediaanScreen.Profile.name) {
                ProfileScreen(drawerState, loggedInUserId, profileRepository)
            }
            composable(
                route = "${MyMediaanScreen.Profile.name}/{profileId}",
                arguments = listOf(navArgument("profileId") { type = NavType.StringType })
            ) {
                val profileId = it.arguments?.getString("profileId")
                ProfileScreen(drawerState, profileId, profileRepository)
            }
            composable(route = MyMediaanScreen.AllColleagues.name) {
                AllColleaguesScreen(drawerState, navController, profileRepository)
            }
        }
    }
}

@Composable
fun MyMediaanApp() {
    MainNavigation()
}

@Preview(showBackground = true)
@Composable
fun MyMediaanAppPreview() {
    MyMediaanTheme {
        MyMediaanApp()
    }
}