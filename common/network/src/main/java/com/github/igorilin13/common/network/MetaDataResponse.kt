package com.github.igorilin13.common.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class MetaDataResponse(
    @Json(name = "next_cursor")
    val nextCursor: Int?,
)
