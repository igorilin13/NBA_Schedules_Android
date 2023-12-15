package com.github.igorilin13.feature.settings.impl.settings.composables

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.igorilin13.common.ui.getTeamLogo
import com.github.igorilin13.common.ui.theme.NBASchedulesTheme
import com.github.igorilin13.data.teams.api.Team
import com.github.igorilin13.feature.settings.R
import com.github.igorilin13.feature.settings.impl.settings.state.FavoriteTeamSettingState

@Composable
internal fun FavoriteTeamSetting(state: FavoriteTeamSettingState, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            stringResource(R.string.setting_favorite_team),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )

        when (state) {
            FavoriteTeamSettingState.Error -> Text(
                stringResource(R.string.favorite_team_error),
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(end = 8.dp)
            )

            is FavoriteTeamSettingState.HasFavorite -> {
                val context = LocalContext.current
                Image(
                    painterResource(context.getTeamLogo(state.team.id)),
                    contentScale = ContentScale.Inside,
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )
                Text(
                    state.team.name,
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }

            FavoriteTeamSettingState.LoadingInfo -> CircularProgressIndicator(
                strokeWidth = 2.dp,
                modifier = Modifier
                    .padding(end = 12.dp)
                    .size(20.dp)
            )

            FavoriteTeamSettingState.NoFavorite -> Text(
                stringResource(R.string.value_no_favorite_team),
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(end = 24.dp)
            )
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HasFavoritePreview() {
    NBASchedulesTheme {
        FavoriteTeamSetting(
            state = FavoriteTeamSettingState.HasFavorite(
                team = Team(14, "Lakers", "LA Lakers")
            )
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LoadingPreview() {
    NBASchedulesTheme {
        FavoriteTeamSetting(state = FavoriteTeamSettingState.LoadingInfo)
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ErrorPreview() {
    NBASchedulesTheme {
        FavoriteTeamSetting(state = FavoriteTeamSettingState.Error)
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun NoFavoritePreview() {
    NBASchedulesTheme {
        FavoriteTeamSetting(state = FavoriteTeamSettingState.NoFavorite)
    }
}