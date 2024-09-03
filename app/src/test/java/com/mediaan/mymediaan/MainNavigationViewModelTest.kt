package com.mediaan.mymediaan

import com.mediaan.mymediaan.model.Office
import com.mediaan.mymediaan.model.TwoTruthsOneLieEntity
import com.mediaan.mymediaan.repository.ProfileRepository
import com.mediaan.mymediaan.viewModel.MainNavigationViewModel
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlinx.coroutines.test.runTest

class MainNavigationViewModelTest {
    private val viewModel = MainNavigationViewModel()
    private val profileRepository = ProfileRepository()

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

    @Test
    fun mainNavigationViewModel_UserCreatedNewProfile_NewProfileIsValid_AndAddedToExistingList() = runTest {
        val id = "me"
        val firstName = "John"
        val lastName = "Smith"
        val age = "19"
        val nickName = "Hawk"
        val office = Office.HEERLEN
        val firstTruth = "This is test"
        val secondTruth = "Test will succeed"
        val lie = "Test was written by AI"

        val newProfile = viewModel.createNewProfile(
            id = id,
            firstName = firstName,
            lastName = lastName,
            age = age.toIntOrNull() ?: 0,
            nickName = nickName,
            office = office,
            twoTruthsOneLie = listOf(
                TwoTruthsOneLieEntity(firstTruth, true),
                TwoTruthsOneLieEntity(secondTruth, true),
                TwoTruthsOneLieEntity(lie, false)
            ),
        )
        assertEquals(newProfile.id, id)
        assertEquals(newProfile.firstName, firstName)
        assertEquals(newProfile.age, age.toIntOrNull())
        assertEquals(newProfile.twoTruthsOneLie.count(), 3)

        profileRepository.addProfile(newProfile)
        val profiles = profileRepository.getAllProfiles()
        assertEquals(profiles.last(), newProfile)
    }
}