package com.github.igorilin13.common.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class MetaDataResponse(
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "current_page")
    val currentPage: Int,
    @Json(name = "next_page")
    val nextPage: Int?,
    @Json(name = "per_page")
    val perPage: Int,
    @Json(name = "total_count")
    val totalCount: Int
)
