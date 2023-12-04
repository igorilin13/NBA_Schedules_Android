package com.github.igorilin13.feature.favorite.impl.onboarding.composables

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.igorilin13.common.ui.composables.ErrorDisplay
import com.github.igorilin13.common.ui.composables.LoadingDisplay
import com.github.igorilin13.common.ui.theme.NBASchedulesTheme
import com.github.igorilin13.data.teams.api.Team
import com.github.igorilin13.feature.favorite.R
import com.github.igorilin13.feature.favorite.impl.onboarding.state.SelectFavoriteOnboardingState

@Composable
internal fun SelectFavoriteOnboardingScreen(
    state: SelectFavoriteOnboardingState,
    onSkipClick: () -> Unit,
    onTeamClick: (Team) -> Unit,
    onConfirmClick: () -> Unit
) {
    Scaffold(
        topBar = { TopBar(onSkipClick) },
        bottomBar = {
            if (state is SelectFavoriteOnboardingState.Display && state.selectedId != null) {
                Button(
                    onClick = onConfirmClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                        .navigationBarsPadding()
                ) {
                    Text(stringResource(R.string.action_continue))
                }
            }
        }
    ) { paddingValues ->
        Box(Modifier.padding(paddingValues)) {
            when (state) {
                is SelectFavoriteOnboardingState.Display -> {
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(minSize = 128.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        items(state.teams, key = { it.id }) { team ->
                            TeamCard(
                                team = team,
                                isSelected = state.selectedId == team.id,
                                onClick = onTeamClick
                            )
                        }
                    }
                }

                SelectFavoriteOnboardingState.Error -> ErrorDisplay(
                    message = stringResource(R.string.team_list_load_error),
                    Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                )

                SelectFavoriteOnboardingState.Loading -> LoadingDisplay(Modifier.fillMaxSize())
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DisplayPreview() {
    NBASchedulesTheme {
        SelectFavoriteOnboardingScreen(
            state = SelectFavoriteOnboardingState.Display(
                teams = List(30) { Team(id = it + 1, name = "Name", fullName = "Team ${it + 1}") },
                selectedId = 2
            ),
            onSkipClick = {},
            onTeamClick = {},
            onConfirmClick = {}
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ErrorPreview() {
    NBASchedulesTheme {
        SelectFavoriteOnboardingScreen(
            state = SelectFavoriteOnboardingState.Error,
            onSkipClick = {},
            onTeamClick = {},
            onConfirmClick = {}
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LoadingPreview() {
    NBASchedulesTheme {
        SelectFavoriteOnboardingScreen(
            state = SelectFavoriteOnboardingState.Loading,
            onSkipClick = {},
            onTeamClick = {},
            onConfirmClick = {}
        )
    }
}
