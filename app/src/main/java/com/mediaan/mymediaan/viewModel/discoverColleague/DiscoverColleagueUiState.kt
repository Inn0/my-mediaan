package com.mediaan.mymediaan.viewModel.discoverColleague

import com.mediaan.mymediaan.model.Profile

data class DiscoverColleagueUiState(
    val profiles: List<Profile> = emptyList(),
    val loggedInUserProfile: String = "",
    val matchingProfiles: List<Profile> = emptyList()
)
