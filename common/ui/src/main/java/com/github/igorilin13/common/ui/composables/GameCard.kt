package com.github.igorilin13.common.ui.composables

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.igorilin13.common.ui.R
import com.github.igorilin13.common.ui.getTeamLogo
import com.github.igorilin13.common.ui.theme.NBASchedulesTheme

@Composable
fun GameCard(
    homeTeamId: Int,
    homeTeamName: String,
    visitorTeamId: Int,
    visitorTeamName: String,
    date: String,
    timeLeft: String?,
    hasScores: Boolean,
    homeTeamScore: Int,
    visitorTeamScore: Int,
    isPostseason: Boolean,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = if (isPostseason) {
                        stringResource(R.string.playoff_with_date_format, date)
                    } else {
                        date
                    },
                    style = MaterialTheme.typography.bodySmall
                )
                if (timeLeft != null) {
                    Text(timeLeft, style = MaterialTheme.typography.labelMedium)
                }
            }
            Box(
                Modifier.padding(top = 8.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TeamInfo(homeTeamId, homeTeamName, Alignment.Start)
                    TeamInfo(visitorTeamId, visitorTeamName, Alignment.End)
                }

                GameScore(
                    hasScores = hasScores,
                    homeTeamScore = homeTeamScore,
                    visitorTeamScore = visitorTeamScore,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 90.dp)
                )
            }
        }
    }
}

@Composable
private fun TeamInfo(
    teamId: Int,
    name: String,
    alignment: Alignment.Horizontal,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Column(modifier, horizontalAlignment = alignment) {
        Image(
            painterResource(context.getTeamLogo(teamId)),
            contentDescription = name,
            contentScale = ContentScale.Inside,
            modifier = Modifier.size(48.dp)
        )
        Text(
            name,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
private fun GameScore(
    hasScores: Boolean,
    homeTeamScore: Int,
    visitorTeamScore: Int,
    modifier: Modifier = Modifier
) {
    Row(modifier, horizontalArrangement = Arrangement.SpaceBetween) {
        if (hasScores) {
            CompositionLocalProvider(
                LocalTextStyle provides MaterialTheme.typography.titleLarge
            ) {
                Text(homeTeamScore.toString())
                Text("-")
                Text(visitorTeamScore.toString())
            }
        } else {
            Text(
                stringResource(R.string.label_vs),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ScheduledGamePreview() {
    NBASchedulesTheme {
        GameCard(
            date = "Today, 8pm",
            homeTeamId = 4,
            homeTeamName = "Hornets",
            homeTeamScore = 0,
            timeLeft = null,
            visitorTeamScore = 0,
            visitorTeamId = 8,
            visitorTeamName = "Nuggets",
            hasScores = false,
            isPostseason = true,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LiveGamePreview() {
    NBASchedulesTheme {
        GameCard(
            date = "Today, 8pm",
            homeTeamId = 4,
            homeTeamName = "Hornets",
            visitorTeamId = 8,
            visitorTeamName = "Nuggets",
            homeTeamScore = 123,
            visitorTeamScore = 98,
            hasScores = true,
            timeLeft = "4th quarter",
            isPostseason = false,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
