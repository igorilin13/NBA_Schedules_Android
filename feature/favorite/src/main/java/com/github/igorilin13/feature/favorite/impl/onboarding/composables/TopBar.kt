package com.github.igorilin13.feature.favorite.impl.onboarding.composables

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.github.igorilin13.common.ui.theme.NBASchedulesTheme
import com.github.igorilin13.feature.favorite.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TopBar(onSkipClick: () -> Unit, modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                stringResource(R.string.title_select_team),
                style = MaterialTheme.typography.titleMedium
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        actions = {
            TextButton(
                onClick = onSkipClick,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = MaterialTheme.colorScheme.onBackground
                )
            ) {
                Text(
                    stringResource(R.string.action_skip),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        },
        modifier = modifier
    )
}

@Preview
@Composable
private fun Preview() {
    NBASchedulesTheme {
        TopBar(onSkipClick = {})
    }
}