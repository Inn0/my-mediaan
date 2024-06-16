package com.mediaan.mymediaan

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mediaan.mymediaan.ui.theme.MyMediaanTheme
import com.mediaan.mymediaan.view.DiscoverColleagueScreen
import com.mediaan.mymediaan.view.MyProfileScreen

enum class MyMediaanScreen(@StringRes val title: Int) {
    DiscoverColleague(title = R.string.discover_colleague),
    MyProfile(title = R.string.my_profile)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyMediaanAppBar(
    currentScreen: MyMediaanScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun MyMediaanApp(
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = MyMediaanScreen.valueOf(
        backStackEntry?.destination?.route ?: MyMediaanScreen.DiscoverColleague.name
    )

    Scaffold(
        topBar = {
            MyMediaanAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        NavHost(navController = navController, startDestination = MyMediaanScreen.DiscoverColleague.name) {
            composable(route = MyMediaanScreen.DiscoverColleague.name) {
                DiscoverColleagueScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(innerPadding)
                )
            }
            composable(route = MyMediaanScreen.MyProfile.name) {
                MyProfileScreen(
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyMediaanAppPreview() {
    MyMediaanTheme {
        MyMediaanApp()
    }
}