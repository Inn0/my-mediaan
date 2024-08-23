package com.mediaan.mymediaan.viewModel.ftu

import androidx.lifecycle.ViewModel
import com.mediaan.mymediaan.model.Office
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FtuViewModel(offices: List<Office>) : ViewModel() {
    val minCharName = 3
    val maxCharName = 50
    val minAge = 16
    val maxAge = 122
    val minCharFacts = 10
    val maxCharFacts = 280

    private val _uiState = MutableStateFlow(FtuUiState(selectedOffice = Office.HEERLEN))
    val uiState = _uiState.asStateFlow()

    fun updateFirstName(newName: String) {
        _uiState.update { currentState ->
            currentState.copy(firstName = newName)
        }
        validateProfileCompletion()
    }

    fun updateNickName(newNickName: String) {
        _uiState.update { currentState ->
            currentState.copy(nickName = newNickName)
        }
        validateProfileCompletion()
    }

    fun updateLastName(newName: String) {
        _uiState.update { currentState ->
            currentState.copy(lastName = newName)
        }
        validateProfileCompletion()
    }

    fun updateAge(newAge: String) {
        _uiState.update { currentState ->
            currentState.copy(age = newAge)
        }
        validateProfileCompletion()
    }

    fun updateSelectedOffice(newOffice: Office) {
        _uiState.update { currentState ->
            currentState.copy(selectedOffice = newOffice)
        }
    }

    fun updateIsExpanded() {
        _uiState.update { currentState ->
            currentState.copy(isExpanded = !currentState.isExpanded)
        }
    }

    fun updateFirstTruth(newTruth: String) {
        _uiState.update { currentState ->
            currentState.copy(firstTruth = newTruth)
        }
        validateProfileCompletion()
    }

    fun updateSecondTruth(newTruth: String) {
        _uiState.update { currentState ->
            currentState.copy(secondTruth = newTruth)
        }
        validateProfileCompletion()
    }

    fun updateLie(newLie: String) {
        _uiState.update { currentState ->
            currentState.copy(lie = newLie)
        }
        validateProfileCompletion()
    }

    private fun validateProfileCompletion() {
        _uiState.update { currentState ->
            currentState.copy(
                isProfileComplete = currentState.firstName.isNotEmpty() &&
                        currentState.lastName.isNotEmpty() &&
                        currentState.age.isNotEmpty() &&
                        currentState.firstTruth.isNotEmpty() &&
                        currentState.secondTruth.isNotEmpty() &&
                        currentState.lie.isNotEmpty()
            )
        }
    }
}