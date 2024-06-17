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
fun MyProfileScreen(
    drawerState: DrawerState
) {
    Scaffold(
        topBar = {
            MyMediaanAppBar(
                drawerState = drawerState,
                title = stringResource(MyMediaanScreen.MyProfile.title),
            )
        },
    ) { innerPadding ->
        Text(
            text = "Here is my profile",
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Preview
@Composable
fun MyProfileScreenPreview() {
    MyMediaanTheme {
        MyProfileScreen(
            drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        )
    }
}