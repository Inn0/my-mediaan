package com.mediaan.mymediaan

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mediaan.mymediaan.ui.theme.MyMediaanTheme
import com.mediaan.mymediaan.view.DiscoverColleagueScreen
import com.mediaan.mymediaan.view.MyProfileScreen

enum class MyMediaanScreen() {
    DiscoverColleague,
    MyProfile
}

@Composable
fun MyMediaanApp(
    navController: NavHostController = rememberNavController()
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
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