package com.mediaan.mymediaan.view.ftu

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mediaan.mymediaan.R
import com.mediaan.mymediaan.ui.theme.MediaanPrimary
import com.mediaan.mymediaan.ui.theme.MyMediaanTheme
import com.mediaan.mymediaan.ui.theme.Typography
import com.mediaan.mymediaan.viewModel.MyMediaanScreen

@Composable
fun FtuScreen(navController: NavHostController) {
    val scrollState = rememberScrollState()

    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .verticalScroll(scrollState)
                .fillMaxWidth(),
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
                text = stringResource(id = R.string.ftu_screen_heading),
                style = Typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 56.dp),
                textAlign = TextAlign.Center,
            )
            Text(
                text = stringResource(id = R.string.ftu_screen_subheading),
                style = Typography.bodyMedium,
                modifier = Modifier.padding(top = 24.dp),
                textAlign = TextAlign.Center,
            )
            Button(
                onClick = { navController.navigate(MyMediaanScreen.CreateProfile.name) },
                colors = ButtonDefaults.buttonColors(containerColor = MediaanPrimary),
                modifier = Modifier
                    .padding(top = 56.dp)
                    .padding(bottom = 24.dp)
            ) {
                Text(stringResource(id = R.string.ftu_screen_start_button))
            }
        }
        }
    }
}

@Preview
@Composable
fun FtuScreenPreview() {
    MyMediaanTheme {
        FtuScreen(navController = rememberNavController())
    }
}