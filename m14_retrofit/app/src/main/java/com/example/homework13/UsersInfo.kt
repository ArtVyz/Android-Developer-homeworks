package com.example.homework13

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class UsersInfo(
    val results: List<Results>,
    val info: Info
)
