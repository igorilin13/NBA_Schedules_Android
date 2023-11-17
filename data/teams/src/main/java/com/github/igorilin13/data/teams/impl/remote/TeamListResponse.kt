package com.github.igorilin13.data.teams.impl.remote

import com.github.igorilin13.data.teams.api.Team
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal class TeamListResponse(
    val data: List<TeamResponse>
)

@JsonClass(generateAdapter = true)
internal class TeamResponse(
    val id: Int,
    val abbreviation: String,
    val city: String,
    val conference: String,
    val division: String,
    @Json(name = "full_name")
    val fullName: String,
    val name: String
) {
    fun toModel() = Team(
        id = id,
        fullName = fullName
    )
}