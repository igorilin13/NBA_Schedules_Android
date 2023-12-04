package com.github.igorilin13.feature.team.games.impl.favorite.composables

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.igorilin13.common.ui.theme.NBASchedulesTheme
import com.github.igorilin13.feature.team.games.R

@Composable
internal fun NoFavoriteTeam(onSelectClick: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            stringResource(R.string.message_no_favorite_team),
            style = MaterialTheme.typography.titleMedium
        )
        TextButton(onClick = onSelectClick, modifier = Modifier.padding(top = 8.dp)) {
            Text(stringResource(R.string.action_select).uppercase())
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    NBASchedulesTheme {
        NoFavoriteTeam(onSelectClick = {}, modifier = Modifier.fillMaxSize())
    }
}