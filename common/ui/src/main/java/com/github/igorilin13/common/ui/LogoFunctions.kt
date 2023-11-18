package com.github.igorilin13.common.ui

import android.annotation.SuppressLint
import android.content.Context

@SuppressLint("DiscouragedApi")
fun Context.getTeamLogo(teamId: Int, fallback: Int = R.drawable.logo_nba): Int {
    val resourceName = "logo$teamId"
    return resources.getIdentifier(resourceName, "drawable", packageName)
        .takeIf { it != 0 }
        ?: fallback
}