package com.mediaan.mymediaan.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mediaan.mymediaan.ui.theme.MyMediaanTheme
import com.mediaan.mymediaan.viewModel.MyMediaanScreen

@Composable
fun DiscoverColleagueScreen(
    drawerState: DrawerState
) {
    Scaffold(
        topBar = {
            MyMediaanAppBar(
                drawerState = drawerState,
                title = stringResource(MyMediaanScreen.DiscoverColleague.title),
            )
        },
    ) { innerPadding ->
        Text(
            text = "Time to discover colleague",
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Preview
@Composable
fun DiscoverColleagueScreenPreview() {
    MyMediaanTheme {
        DiscoverColleagueScreen(
            drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        )
    }
}