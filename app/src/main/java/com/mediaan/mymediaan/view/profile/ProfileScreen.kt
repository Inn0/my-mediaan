package com.mediaan.mymediaan.view.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mediaan.mymediaan.R
import com.mediaan.mymediaan.model.Profile
import com.mediaan.mymediaan.model.TwoTruthsOneLieEntity
import com.mediaan.mymediaan.repository.ProfileRepository
import com.mediaan.mymediaan.ui.theme.MediaanPrimary
import com.mediaan.mymediaan.ui.theme.Typography
import com.mediaan.mymediaan.view.MyMediaanAppBar

@Composable
fun ProfileScreen(
    drawerState: DrawerState,
    profileId: String?,
    profileRepository: ProfileRepository
){
    var profile by remember { mutableStateOf<Profile?>(null) }

    LaunchedEffect(profileId) {
        profile = profileId?.let { profileRepository.getProfileById(it) }
    }
    val scrollState = rememberScrollState()

    Scaffold(topBar = {
        MyMediaanAppBar(
            drawerState = drawerState,
            title = stringResource(id = R.string.profile_screen_title),
        )
    }){ padding ->
        profile?.let {
            Column(modifier = Modifier
                .padding(padding)
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .verticalScroll(scrollState)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = it.avatarIcon),
                        contentDescription = "avatarIcon",
                        modifier = Modifier
                            .size(48.dp)
                            .padding(8.dp),
                        tint = MediaanPrimary,
                    )
                    Text(
                        text = "${it.firstName} \"${it.nickName}\" ${it.lastName}",
                        style = Typography.displaySmall,
                        fontWeight = FontWeight.Bold
                    )
                }
                AttributeRow(title = "Age", value = it.age.toString())
                AttributeRow(title = "Office", value = it.office.toString())

                if(it.interests.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Interests:",
                        style = Typography.titleLarge,
                        modifier = Modifier.padding(8.dp),
                        fontWeight = FontWeight.SemiBold
                    )
                    it.interests.forEach {
                        Text(
                            text = it.name,
                            style = Typography.bodyLarge,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Two truths, one lie:",
                    style = Typography.titleLarge,
                    modifier = Modifier.padding(8.dp),
                    fontWeight = FontWeight.SemiBold
                )
                it.twoTruthsOneLie.forEach { 
                    TruthOrLie(value = it)
                }
            }
        } ?: run {
            Text(stringResource(id = R.string.profile_screen_no_profile))
        }
    }
}

@Composable
private fun AttributeRow(title: String, value: String) {
    Row(modifier = Modifier.padding(8.dp)) {
        Text(
            text = "$title:",
            style = Typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(text = value, style = Typography.bodyLarge)
    }
}

@Composable
private fun TruthOrLie(value: TwoTruthsOneLieEntity) {
    Row(modifier = Modifier.padding(8.dp)) {
        Text(
            text = "${if (value.isTrue) "Truth" else "Lie"}:",
            style = Typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            color = if (value.isTrue) Green else Red,
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(
            text = value.statement,
            style = Typography.bodyLarge,
        )
    }
}