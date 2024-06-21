package com.example.homework13

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

<<<<<<< HEAD
@JsonClass(generateAdapter = true)
data class UsersInfo(
    @Json(name = "results") val results: List<Results>
=======
@JsonClass (generateAdapter = true)
data class UsersInfo(
    @Json( name = "name") val name:String
>>>>>>> origin/master
)
