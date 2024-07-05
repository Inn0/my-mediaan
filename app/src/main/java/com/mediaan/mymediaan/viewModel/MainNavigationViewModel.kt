package com.mediaan.mymediaan.viewModel

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.lifecycle.ViewModel
import com.mediaan.mymediaan.R
import com.mediaan.mymediaan.model.DrawerItem
import com.mediaan.mymediaan.model.Office
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

enum class MyMediaanScreen(@StringRes val title: Int) {
    Ftu(title = R.string.ftu),
    CreateProfile(title = R.string.create_profile),
    DiscoverColleague(title = R.string.discover_colleague),
    Profile(title = R.string.profile),
    AllColleagues(title = R.string.all_colleagues),
}

class MainNavigationViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MainNavigationUiState())
    val uiState: StateFlow<MainNavigationUiState> = _uiState.asStateFlow()

    val drawerItems = arrayOf(
        DrawerItem(
            Icons.Filled.Search,
            Icons.Outlined.Search,
            MyMediaanScreen.DiscoverColleague.name,
            MyMediaanScreen.DiscoverColleague.name
        ),
        DrawerItem(
            Icons.Filled.Person,
            Icons.Outlined.Person,
            MyMediaanScreen.Profile.name,
            MyMediaanScreen.Profile.name
        ),
        DrawerItem(
            Icons.Filled.List,
            Icons.Outlined.List,
            MyMediaanScreen.AllColleagues.name,
            MyMediaanScreen.AllColleagues.name
        ),
    )

    val offices: List<String> = Office.entries.map { it.toString() }

    init {
       _uiState.value = MainNavigationUiState(currentSelectedItemIndex = 0)
    }

    fun updateSelectedItemIndex(newIndex: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                currentSelectedItemIndex = newIndex
            )
        }
    }

    fun updateIsOnboardingDone() {
        _uiState.update { currentState ->
            currentState.copy(
                isOnboardingDone = true
            )
        }
    }
}