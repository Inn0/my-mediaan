package com.mediaan.mymediaan

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mediaan.mymediaan.model.DrawerItem
import com.mediaan.mymediaan.ui.theme.MyMediaanTheme
import com.mediaan.mymediaan.view.DiscoverColleagueScreen
import com.mediaan.mymediaan.view.MyProfileScreen
import com.mediaan.mymediaan.viewModel.MainNavigationViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

enum class MyMediaanScreen(@StringRes val title: Int) {
    DiscoverColleague(title = R.string.discover_colleague),
    MyProfile(title = R.string.my_profile)
}

// TODO: move to MainNavigationViewModel
private val drawerItems = arrayOf(
    DrawerItem(
        Icons.Filled.Search,
        Icons.Outlined.Search,
        MyMediaanScreen.DiscoverColleague.name,
        MyMediaanScreen.DiscoverColleague.name
    ),
    DrawerItem(
        Icons.Filled.Person,
        Icons.Outlined.Person,
        MyMediaanScreen.MyProfile.name,
        MyMediaanScreen.MyProfile.name
    )
)

@Composable
private fun DrawerContent(
    viewModel: MainNavigationViewModel,
    items: Array<DrawerItem>,
    onMenuClick: (String) -> Unit // TODO: check if this is actually needed?
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
    mainNavigationViewModel: MainNavigationViewModel = viewModel()
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerContent(
                    viewModel = mainNavigationViewModel,
                    items = drawerItems
                ) { route ->
                    coroutineScope.launch {
                        drawerState.close()
                    }
                    navController.navigate(route)
                }
            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = MyMediaanScreen.DiscoverColleague.name,
            modifier = Modifier
                .fillMaxSize()
        ) {
            composable(route = MyMediaanScreen.DiscoverColleague.name) {
                DiscoverColleagueScreen(drawerState)
            }
            composable(route = MyMediaanScreen.MyProfile.name) {
                MyProfileScreen(drawerState)
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