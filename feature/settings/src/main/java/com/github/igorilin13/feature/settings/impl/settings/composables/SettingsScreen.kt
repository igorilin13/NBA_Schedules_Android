package com.github.igorilin13.feature.settings.impl.settings.composables

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.igorilin13.common.ui.theme.NBASchedulesTheme
import com.github.igorilin13.data.teams.api.Team
import com.github.igorilin13.feature.settings.R
import com.github.igorilin13.feature.settings.impl.settings.state.FavoriteTeamSettingState
import com.github.igorilin13.feature.settings.impl.settings.state.SettingsState

@Composable
internal fun SettingsScreen(
    state: SettingsState,
    onHideScoresChange: (Boolean) -> Unit,
    onSelectFavoriteTeamClick: () -> Unit
) {
    Column(Modifier.fillMaxSize()) {
        Text(
            stringResource(R.string.title_settings),
            Modifier.padding(16.dp),
            style = MaterialTheme.typography.titleLarge.copy(fontSize = 32.sp)
        )

        if (state.shouldHideScores != null) {
            Row(
                modifier = Modifier
                    .clickable { onHideScoresChange(!state.shouldHideScores) }
                    .padding(start = 24.dp, end = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    stringResource(R.string.setting_hide_scores),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.weight(1f)
                )
                Switch(
                    checked = state.shouldHideScores,
                    onCheckedChange = onHideScoresChange
                )
            }
        }

        FavoriteTeamSetting(
            state = state.favoriteTeamState,
            modifier = Modifier
                .padding(top = 12.dp)
                .clickable(onClick = onSelectFavoriteTeamClick)
                .padding(start = 24.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    NBASchedulesTheme {
        SettingsScreen(
            state = SettingsState(
                shouldHideScores = true,
                favoriteTeamState = FavoriteTeamSettingState.HasFavorite(Team(14, "Team", "Team"))
            ),
            onHideScoresChange = {},
            onSelectFavoriteTeamClick = {}
        )
    }
}