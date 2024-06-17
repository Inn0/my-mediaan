package com.mediaan.mymediaan.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainNavigationViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(MainNavigationUiState())
    val uiState: StateFlow<MainNavigationUiState> = _uiState.asStateFlow()

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