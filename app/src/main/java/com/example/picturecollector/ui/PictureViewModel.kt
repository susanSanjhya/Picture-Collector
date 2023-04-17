package com.example.picturecollector.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.picturecollector.PicturesApplication
import com.example.picturecollector.data.PicturesRepository
import com.example.picturecollector.network.Picture
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface PicturesUiState {
    data class Success(val pictures: List<Picture>) : PicturesUiState
    object Error : PicturesUiState
    object Loading : PicturesUiState
}

class PictureViewModel(private val picturesRepository: PicturesRepository) : ViewModel() {
    var uiState: PicturesUiState by mutableStateOf(PicturesUiState.Loading)
        private set

    init {
        getPictures()
    }

    fun getPictures() {
        viewModelScope.launch {
            uiState = try {
                val listResult = picturesRepository.getPictures()
//                val result = picturesRepository.getPictures()[11]
                PicturesUiState.Success(listResult)
            } catch (e: IOException) {
                PicturesUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as PicturesApplication)
                val picturesRepository = application.container.picturesRepository
                PictureViewModel(picturesRepository = picturesRepository)
            }
        }
    }
}