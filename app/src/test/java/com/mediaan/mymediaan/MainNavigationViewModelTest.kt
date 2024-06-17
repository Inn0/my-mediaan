package com.mediaan.mymediaan

import com.mediaan.mymediaan.viewModel.MainNavigationViewModel
import org.junit.Test
import org.junit.Assert.*

class MainNavigationViewModelTest {
    private val viewModel = MainNavigationViewModel()

    @Test
    fun mainNavigationViewModel_UserPressedDrawerItem_SelectedItemIndexUpdated() {
        var currentMainNavigationUiState = viewModel.uiState.value
        val initialSelectedItemIndex = currentMainNavigationUiState.currentSelectedItemIndex
        assertEquals(initialSelectedItemIndex, 0)

        val newIndex: Int = 1
        viewModel.updateSelectedItemIndex(newIndex)
        currentMainNavigationUiState = viewModel.uiState.value
        val updatedSelectedItemIndex = currentMainNavigationUiState.currentSelectedItemIndex
        assertEquals(updatedSelectedItemIndex, newIndex)
    }
}