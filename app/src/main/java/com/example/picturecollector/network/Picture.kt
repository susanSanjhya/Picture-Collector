package com.example.picturecollector.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Picture(
    val id: Int,
    @SerialName(value = "picture_url")
    val url: String
)