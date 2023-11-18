package com.github.igorilin13.common.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.github.igorilin13.common.ui.theme.NBASchedulesTheme

@Composable
fun LoadingDisplay(modifier: Modifier = Modifier) {
    Box(modifier, contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Preview
@Composable
private fun Preview() {
    NBASchedulesTheme {
        LoadingDisplay(Modifier.fillMaxSize())
    }
}