package com.github.igorilin13.data.teams.impl.remote

import com.github.igorilin13.common.network.TeamResponse
import com.github.igorilin13.data.teams.api.Team
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal class TeamListResponse(
    val data: List<TeamResponse>
)

fun TeamResponse.toModel() = Team(
    id = id,
    name = name,
    fullName = fullName
)