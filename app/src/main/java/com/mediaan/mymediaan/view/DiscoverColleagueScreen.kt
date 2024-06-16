package com.mediaan.mymediaan.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mediaan.mymediaan.ui.theme.MyMediaanTheme

@Composable
fun DiscoverColleagueScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(modifier = modifier) { innerPadding ->
        Text(
            text = "Time to discover colleague",
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Preview
@Composable
fun DiscoverColleagueScreenPreview() {
    MyMediaanTheme {
        DiscoverColleagueScreen(
            modifier = Modifier.fillMaxSize()
        )
    }
}