package com.example.homework13

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UsersInfo(
    @Json(name = "results") val results: List<Results>
)
