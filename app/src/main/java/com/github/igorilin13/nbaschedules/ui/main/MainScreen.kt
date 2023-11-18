package com.github.igorilin13.nbaschedules.ui.main

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.igorilin13.common.ui.theme.NBASchedulesTheme

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation()
        }
    ) { paddingValues ->
        NavHost(
            navController,
            startDestination = "test",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("test") {
                Text(text = "Hello")
            }
        }
    }
}

@Composable
private fun BottomNavigation(modifier: Modifier = Modifier) {
    NavigationBar(modifier) {
        MainNavigationItem.values().forEach { item ->
            NavigationBarItem(
                selected = item == MainNavigationItem.LEAGUE,
                label = {
                    Text(stringResource(item.label), style = MaterialTheme.typography.bodyMedium)
                },
                icon = {
                    Icon(item.icon, contentDescription = stringResource(item.label))
                },
                onClick = {

                }
            )
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    NBASchedulesTheme {

    }
}
