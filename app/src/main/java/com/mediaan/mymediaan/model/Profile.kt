package com.mediaan.mymediaan.model

import androidx.annotation.DrawableRes
import java.util.UUID

data class Profile(
    val id: String = UUID.randomUUID().toString(),
    val firstName: String,
    val lastName: String,
    val age: Int,
    val nickName: String? = null,
    val office: Office = Office.HEERLEN,
    val interests: List<String> = emptyList(),
    val twoTruthsOneLie: List<TwoTruthsOneLieEntity> = emptyList(),
    @DrawableRes
    val avatarIcon: Int,
)

enum class Office {
    HEERLEN, HASSELT, DUSSELDORF;

    override fun toString(): String {
        val nameLower = name.lowercase()
        return nameLower.replaceFirstChar { it.uppercase() }
    }
}

data class TwoTruthsOneLieEntity(
    val statement: String,
    val isTrue: Boolean,
)