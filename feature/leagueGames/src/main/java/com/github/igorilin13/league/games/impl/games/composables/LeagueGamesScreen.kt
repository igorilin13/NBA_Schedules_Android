package com.github.igorilin13.league.games.impl.games.composables

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.github.igorilin13.feature.league.games.R
import com.github.igorilin13.league.games.impl.games.state.LeagueGamesState
import java.time.ZonedDateTime
import com.github.igorilin13.common.ui.R as utilR

@Composable
internal fun LeagueGamesScreen(state: LeagueGamesState) {
    when (state) {
        LeagueGamesState.Loading -> {
            LoadingDisplay(Modifier.fillMaxSize())
        }

        is LeagueGamesState.DisplayData -> {
            LazyColumn(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                item {
                    Text(
                        stringResource(R.string.title_today),
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(start = 12.dp)
                    )
                }
                items(state.games) { game ->
                    GameCard(
                        homeTeamId = game.model.homeTeam.id,
                        homeTeamName = game.model.homeTeam.name,
                        visitorTeamId = game.model.visitorTeam.id,
                        visitorTeamName = game.model.visitorTeam.name,
                        date = game.formattedDate,
                        timeLeft = game.model.time,
                        hasScores = game.showScores && game.model.gameStatus != GameStatus.SCHEDULED,
                        homeTeamScore = game.model.homeTeamScore,
                        visitorTeamScore = game.model.visitorTeamScore,
                        isPostseason = game.model.postseason,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

        LeagueGamesState.Error -> {
            ErrorDisplay(
                stringResource(utilR.string.games_load_error),
                Modifier.fillMaxSize()
            )
        }

        LeagueGamesState.NoGamesAvailable -> {
            ErrorDisplay(
                stringResource(utilR.string.no_games_message),
                Modifier.fillMaxSize()
            )
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DataPreview() {
    NBASchedulesTheme {
        LeagueGamesScreen(
            state = LeagueGamesState.DisplayData(
                List(10) {
                    GameItem(
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
                }
            )
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LoadingPreview() {
    NBASchedulesTheme {
        LeagueGamesScreen(
            state = LeagueGamesState.Loading
        )
    }
}


@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ErrorPreview() {
    NBASchedulesTheme {
        LeagueGamesScreen(
            state = LeagueGamesState.Error
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun NoGamesPreview() {
    NBASchedulesTheme {
        LeagueGamesScreen(
            state = LeagueGamesState.NoGamesAvailable
        )
    }
}