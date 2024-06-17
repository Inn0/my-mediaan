package com.mediaan.mymediaan.viewModel

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.lifecycle.ViewModel
import com.mediaan.mymediaan.R
import com.mediaan.mymediaan.model.DrawerItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

enum class MyMediaanScreen(@StringRes val title: Int) {
    DiscoverColleague(title = R.string.discover_colleague),
    MyProfile(title = R.string.my_profile)
}

class MainNavigationViewModel: ViewModel() {
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
            MyMediaanScreen.MyProfile.name,
            MyMediaanScreen.MyProfile.name
        )
    )

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
}