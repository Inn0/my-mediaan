package com.mediaan.mymediaan.model

import androidx.compose.ui.graphics.vector.ImageVector

data class DrawerItem(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val title: String,
    val route: String
)