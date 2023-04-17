package com.example.picturecollector.data

import com.example.picturecollector.network.Picture
import com.example.picturecollector.network.PictureApiService

interface PicturesRepository {
    suspend fun getPictures(): List<Picture>
}

class DefaultPictureRepository(private val pictureApiService: PictureApiService) :
    PicturesRepository {
    override suspend fun getPictures(): List<Picture> {
        return pictureApiService.getPictures()
    }

}