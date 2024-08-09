package com.example.homewrk16.data.api

import com.example.homewrk16.data.UsefulActivityDto
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://favqs.com"

object RetrofitInstance {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val searchUserApi: UsefulActivityAPI = retrofit.create(UsefulActivityAPI::class.java)
}

interface UsefulActivityAPI {
    @GET("/api/qotd")
    suspend fun getActivity(): UsefulActivityDto
}