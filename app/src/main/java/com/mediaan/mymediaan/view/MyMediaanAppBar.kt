package com.mediaan.mymediaan.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.mediaan.mymediaan.ui.theme.MyMediaanTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyMediaanAppBar(
    drawerState: DrawerState,
    title: String,
    navIcon: ImageVector? = Icons.Filled.Menu,
    iconDescr: String? = "Menu",
    onNavIconClick: (() -> Unit)? = null,
) {
    val coroutineScope = rememberCoroutineScope()
    val onClick: () -> Unit = onNavIconClick ?: {
        coroutineScope.launch {
            drawerState.apply {
                if (isClosed) open() else close()
            }
        }
    }

    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = onClick) {
                Icon(
                    imageVector = navIcon ?: Icons.Filled.Menu,
                    contentDescription = iconDescr
                )
            }
        }
    )
}

@Preview(widthDp = 300)
@Composable
fun MyMediaanAppBarPreview() {
    MyMediaanTheme {
        MyMediaanAppBar(
            drawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
            title = "Title",
            navIcon = Icons.Filled.Menu,
        )
    }
}