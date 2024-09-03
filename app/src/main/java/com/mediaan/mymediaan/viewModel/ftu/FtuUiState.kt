package com.mediaan.mymediaan.viewModel.ftu

import com.mediaan.mymediaan.model.Office

data class FtuUiState(
    val firstName: String = "",
    val nickName: String = "",
    val lastName: String = "",
    val age: String = "",
    val selectedOffice: Office = Office.HEERLEN,
    val isProfileComplete: Boolean = false,
    val isExpanded: Boolean = false,
    val firstTruth: String = "",
    val secondTruth: String = "",
    val lie: String = ""
)
