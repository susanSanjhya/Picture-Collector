package com.example.picturecollector

import android.app.Application
import com.example.picturecollector.data.AppContainer
import com.example.picturecollector.data.DefaultAppContainer

class PicturesApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}