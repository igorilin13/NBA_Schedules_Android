package com.github.igorilin13.feature.favorite.impl.core.composables

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.igorilin13.common.ui.getTeamLogo
import com.github.igorilin13.common.ui.theme.NBASchedulesTheme
import com.github.igorilin13.data.teams.api.Team

@Composable
internal fun TeamCard(
    team: Team,
    isSelected: Boolean,
    onClick: (Team) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val logo = context.getTeamLogo(team.id)

    Surface(
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick(team) }
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painterResource(logo),
                contentDescription = null,
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .height(128.dp)
                    .fillMaxWidth()
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .heightIn(min = 64.dp)
            ) {
                RadioButton(
                    selected = isSelected,
                    onClick = null,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    team.fullName,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    maxLines = 2
                )
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    NBASchedulesTheme {
        TeamCard(
            team = Team(id = 14, name = "Lakers", fullName = "Los Angeles Lakers"),
            isSelected = false,
            onClick = {}
        )
    }
}