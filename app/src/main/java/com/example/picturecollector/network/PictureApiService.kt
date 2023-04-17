package com.example.picturecollector.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

private const val BASE_URL = "http://10.0.2.2:3000/api/v1/"
// if want to skip unwanted keys in json response
//val json = Json { ignoreUnknownKeys = true }
//    .addConverterFactory(json.asConverterFactory(MediaType.get("application/json")))

@OptIn(ExperimentalSerializationApi::class)
private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface PictureApiService {
    @GET("images")
    suspend fun getPictures(): List<Picture>
}