package com.github.igorilin13.common.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class TeamResponse(
    val id: Int,
    val abbreviation: String,
    val city: String,
    val conference: String,
    val division: String,
    @Json(name = "full_name")
    val fullName: String,
    val name: String
)