package com.mediaan.mymediaan.view.profile

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.mediaan.mymediaan.repository.ProfileRepository
import com.mediaan.mymediaan.view.MyMediaanAppBar

@Composable
fun ProfileScreen(
    drawerState: DrawerState,
    profileId: String?,
    profileRepository: ProfileRepository = ProfileRepository()
){
    val profile = remember(profileId) {
        profileId?.let { profileRepository.getProfileById(it) }
    }

    Scaffold(topBar = {
        MyMediaanAppBar(
            drawerState = drawerState,
            title = profileId ?: "Empty",
        )
    }){ padding ->
        profile?.let { actualProfile ->
            Column(modifier = Modifier.padding(padding)) {
                Text(text = "First Name: ${actualProfile.firstName}")
                Text(text = "Last Name: ${actualProfile.lastName}")
                Text(text = "Age: ${actualProfile.age}")
                Text(text = "Nickname: ${actualProfile.nickName ?: "N/A"}")
                Text(text = "Office: ${actualProfile.office}")
                Text(text = "Interests: ${actualProfile.interests.joinToString()}")
                actualProfile.twoTruthsOneLie.forEach { truthOrLie ->
                    Text(text = "${truthOrLie.statement} - ${if (truthOrLie.isTrue) "True" else "False"}")
                }
            }
        } ?: run {
            Text("No profile found")
        }
    }
}