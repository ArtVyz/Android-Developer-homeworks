package com.example.homework17.data.api

import com.example.homework17.entity.MarsPhotoInfo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.nasa.gov"
object RetrofitInstance {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val searchUserApi: ReceivingPhotoApi = retrofit.create(ReceivingPhotoApi::class.java)
}

interface ReceivingPhotoApi {
    @GET("/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&page=2&api_key=5cVHMlJdnVlhnOLpmErVEg6tPBdC73Z57IhPM3cc")
    suspend fun getUserInfo (@Query ("limit") limit: Int = 1 ) : MarsPhotoInfo
}