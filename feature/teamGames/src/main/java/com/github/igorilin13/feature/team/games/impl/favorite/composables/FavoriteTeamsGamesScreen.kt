package com.github.igorilin13.feature.team.games.impl.favorite.composables

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.igorilin13.common.ui.composables.ErrorDisplay
import com.github.igorilin13.common.ui.composables.GameCard
import com.github.igorilin13.common.ui.composables.LoadingDisplay
import com.github.igorilin13.common.ui.theme.NBASchedulesTheme
import com.github.igorilin13.data.games.api.Game
import com.github.igorilin13.data.games.api.GameStatus
import com.github.igorilin13.data.teams.api.Team
import com.github.igorilin13.domain.game.GameItem
import com.github.igorilin13.feature.team.games.R
import com.github.igorilin13.feature.team.games.impl.favorite.state.FavoriteTeamGamesState
import java.time.ZonedDateTime
import com.github.igorilin13.common.ui.R as utilR

@Composable
internal fun FavoriteTeamGamesScreen(
    state: FavoriteTeamGamesState,
    onSelectFavoriteClick: () -> Unit
) {
    when (state) {
        FavoriteTeamGamesState.Loading -> {
            LoadingDisplay(Modifier.fillMaxSize())
        }

        FavoriteTeamGamesState.NoFavoriteTeam -> {
            NoFavoriteTeam(
                onSelectClick = onSelectFavoriteClick,
                modifier = Modifier.fillMaxSize()
            )
        }

        is FavoriteTeamGamesState.DisplayData -> {
            LazyColumn(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                if (state.nextGame != null) {
                    addSection(R.string.section_next_game, listOf(state.nextGame))
                }
                if (state.previousGame != null) {
                    addSection(R.string.section_previous_game, listOf(state.previousGame))
                }
                if (state.upcomingGames.isNotEmpty()) {
                    addSection(R.string.section_upcoming, state.upcomingGames)
                }
                if (state.previousGames.isNotEmpty()) {
                    addSection(R.string.section_previous, state.previousGames)
                }
            }
        }

        FavoriteTeamGamesState.Error -> {
            ErrorDisplay(
                stringResource(utilR.string.games_load_error),
                Modifier.fillMaxSize()
            )
        }

        FavoriteTeamGamesState.NoGamesAvailable -> {
            ErrorDisplay(
                stringResource(utilR.string.no_games_message),
                Modifier.fillMaxSize()
            )
        }
    }
}

private fun LazyListScope.addSection(@StringRes title: Int, items: List<GameItem>) {
    item {
        Text(
            stringResource(title),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(start = 12.dp)
        )
    }
    items(items) { item ->
        GameCard(
            homeTeamId = item.model.homeTeam.id,
            homeTeamName = item.model.homeTeam.name,
            visitorTeamId = item.model.visitorTeam.id,
            visitorTeamName = item.model.visitorTeam.name,
            date = item.formattedDate,
            timeLeft = item.model.time,
            hasScores = item.showScores && item.model.gameStatus != GameStatus.SCHEDULED,
            homeTeamScore = item.model.homeTeamScore,
            visitorTeamScore = item.model.visitorTeamScore,
            isPostseason = item.model.postseason,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DataPreview() {
    val mockItem = GameItem(
        model = Game(
            id = 0,
            date = ZonedDateTime.now(),
            homeTeam = Team(1, "Team", "Team name full"),
            homeTeamScore = 0,
            postseason = false,
            time = null,
            visitorTeamScore = 0,
            visitorTeam = Team(2, "Team", "Team name full"),
            gameStatus = GameStatus.SCHEDULED
        ),
        formattedDate = "Today",
        showScores = true
    )

    NBASchedulesTheme {
        FavoriteTeamGamesScreen(
            state = FavoriteTeamGamesState.DisplayData(
                previousGame = mockItem,
                nextGame = mockItem,
                upcomingGames = List(10) { mockItem },
                previousGames = List(10) { mockItem }
            ),
            onSelectFavoriteClick = {}
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LoadingPreview() {
    NBASchedulesTheme {
        FavoriteTeamGamesScreen(
            state = FavoriteTeamGamesState.Loading,
            onSelectFavoriteClick = {}
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun NoFavoritePreview() {
    NBASchedulesTheme {
        FavoriteTeamGamesScreen(
            state = FavoriteTeamGamesState.NoFavoriteTeam,
            onSelectFavoriteClick = {}
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ErrorPreview() {
    NBASchedulesTheme {
        FavoriteTeamGamesScreen(
            state = FavoriteTeamGamesState.Error,
            onSelectFavoriteClick = {}
        )
    }
}