package com.mediaan.mymediaan.viewModel.discoverColleague
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mediaan.mymediaan.model.Profile
import com.mediaan.mymediaan.repository.ProfileRepository
import com.mediaan.mymediaan.viewModel.MainNavigationViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DiscoverColleagueViewModel(
    private val mainNavigationViewModel: MainNavigationViewModel,
    private val profileRepository: ProfileRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(DiscoverColleagueUiState())
    val uiState: StateFlow<DiscoverColleagueUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                val profiles = profileRepository.getAllProfiles()
                _uiState.value = DiscoverColleagueUiState(
                    profiles = profiles
                )
            } catch (exception: Exception) {
                Log.e("DiscoverColleagueVM", "Error loading profiles", exception)
            }
        }
    }

    fun findMatchingColleague(onMatchingProfileFound: (Profile?) -> Unit) {
        val loggedInUserProfile = mainNavigationViewModel.uiState.value.loggedInUserProfile

        if (loggedInUserProfile != null) {
            val matchingProfiles = uiState.value.profiles.filter { profile ->
                profile.id != loggedInUserProfile.id && profile.interests.any { interest ->
                    loggedInUserProfile.interests.contains(interest)
                }
            }
            val sortedMatchingProfiles = sortByMostInterests(loggedInUserProfile, matchingProfiles)

            _uiState.value = _uiState.value.copy(matchingProfiles = sortedMatchingProfiles)
            onMatchingProfileFound(uiState.value.matchingProfiles.firstOrNull())
        } else {
            onMatchingProfileFound(null)
        }
    }

    private fun sortByMostInterests(loggedInUser: Profile, profiles: List<Profile>): List<Profile> {
        return profiles.sortedByDescending { profile ->
            profile.interests.count { interest ->
                loggedInUser.interests.contains(interest)
            }
        }
    }
}