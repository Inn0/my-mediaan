package com.mediaan.mymediaan.viewModel

import com.mediaan.mymediaan.model.Profile

data class MainNavigationUiState(
    val currentSelectedItemIndex: Int = 0,
    val isOnboardingDone: Boolean = false,
    val loggedInUserProfile: Profile? = null
)