package com.mediaan.mymediaan.view.profile

import android.content.res.Resources.Theme
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mediaan.mymediaan.model.Profile
import com.mediaan.mymediaan.ui.theme.MediaanPrimary
import com.mediaan.mymediaan.ui.theme.Typography

@Composable
fun ProfileCard(
    profile: Profile,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(PaddingValues(start = 16.dp, bottom = 16.dp, end = 16.dp))
    ) {
        Row(modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = onClick), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = profile.avatarIcon),
                contentDescription = "avatarIcon",
                modifier = Modifier.padding(8.dp),
                tint = MediaanPrimary
            )
            Column {
                Text(
                    text = "${profile.firstName} \"${profile.nickName}\" ${profile.lastName}",
                    style = Typography.titleMedium,
                    modifier = Modifier.padding(4.dp)
                )
                if(profile.interests.isNotEmpty()) {
                    Text(
                        text = "${profile.interests.count()} interests",
                        style = Typography.bodyLarge,
                        modifier = Modifier.padding(4.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = profile.office.toString(),
                style = Typography.labelMedium,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}