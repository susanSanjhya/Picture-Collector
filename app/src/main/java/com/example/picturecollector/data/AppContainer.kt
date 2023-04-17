package com.example.picturecollector.data

import com.example.picturecollector.network.PictureApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val picturesRepository: PicturesRepository
}

class DefaultAppContainer : AppContainer {
    private val BASE_URL = "http://10.0.2.2:3000/api/v1/"

    @OptIn(ExperimentalSerializationApi::class)
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService: PictureApiService by lazy {
        retrofit.create(PictureApiService::class.java)
    }

    override val picturesRepository: PicturesRepository by lazy {
        DefaultPictureRepository(retrofitService)
    }
}