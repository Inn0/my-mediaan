package com.mediaan.mymediaan.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mediaan.mymediaan.R
import com.mediaan.mymediaan.ui.theme.MediaanPrimary
import com.mediaan.mymediaan.ui.theme.MyMediaanTheme
import com.mediaan.mymediaan.ui.theme.Typography

@Composable
fun FtuScreen(drawerState: DrawerState) {
    val scrollState = rememberScrollState()

    Scaffold() { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .padding(bottom = 64.dp)
                .fillMaxSize()
                .verticalScroll(scrollState),
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.handshake_outline),
                    contentDescription = "FTU icon",
                    modifier = Modifier
                        .size(120.dp),
                    tint = MediaanPrimary,
                )
                Text(
                    text = "Your way to connect with Mediaan",
                    style = Typography.titleLarge,
                    modifier = Modifier.padding(top = 16.dp),
                    fontWeight = FontWeight.SemiBold,
                )
                Text(
                    text = "Find matching colleagues in a gamified way",
                    style = Typography.bodyMedium,
                    modifier = Modifier.padding(top = 16.dp),
                )
                Button(
                    onClick = { /*TODO: navigate to something like profile screen to fill in data; should have "Back" button*/ },
                    colors = ButtonDefaults.buttonColors(containerColor = MediaanPrimary),
                    modifier = Modifier.padding(top = 32.dp)
                ) {
                    Text("Get started")
                }
            }
        }
    }
}

@Preview
@Composable
fun FtuScreenPreview() {
    MyMediaanTheme {
        FtuScreen(
            drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        )
    }
}