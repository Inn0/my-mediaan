package com.mediaan.mymediaan.viewModel.discoverColleague
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mediaan.mymediaan.model.Profile
import com.mediaan.mymediaan.repository.ProfileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DiscoverColleagueViewModel(private val profileRepository: ProfileRepository, loggedInUserId: String) : ViewModel() {
    private val _uiState = MutableStateFlow(DiscoverColleagueUiState())
    val uiState: StateFlow<DiscoverColleagueUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                val profiles = profileRepository.getAllProfiles()
                _uiState.value = DiscoverColleagueUiState(profiles = profiles, loggedInUserId)
            } catch (exception: Exception) {
                Log.e("DiscoverColleagueVM", "Error loading profiles", exception)
            }
        }
    }

    fun findMatchingColleague(onMatchingProfileFound: (Profile?) -> Unit) {
        viewModelScope.launch {
            try {
                val loggedInUserProfile = profileRepository.getProfileById(uiState.value.loggedInUserId)

                val matchingProfiles = uiState.value.profiles.filter { profile ->
                    profile.id != uiState.value.loggedInUserId && profile.interests.any { interest ->
                        loggedInUserProfile.interests.contains(interest)
                    }
                }

                _uiState.value = _uiState.value.copy(matchingProfiles = matchingProfiles)
                onMatchingProfileFound(uiState.value.matchingProfiles.firstOrNull())
            } catch (e: Exception) {
                Log.e("DiscoverColleagueVM", "Error finding matching colleagues", e)
                onMatchingProfileFound(null)
            }
        }
    }
}