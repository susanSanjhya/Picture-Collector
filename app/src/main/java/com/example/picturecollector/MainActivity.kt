package com.example.picturecollector

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.picturecollector.ui.PictureViewModel
import com.example.picturecollector.ui.PictureCollectorApp
import com.example.picturecollector.ui.theme.PictureCollectorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PictureCollectorTheme {
                val viewModel: PictureViewModel = viewModel(factory = PictureViewModel.Factory)
                PictureCollectorApp(viewModel)
            }
        }
    }
}