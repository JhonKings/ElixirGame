package com.example.elixirgame.presentation.viewmodel.listvg

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.elixirgame.domain.VideoGameUseCase

class ViewModelFactory(private val videoGameUseCase: VideoGameUseCase): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VideoGameViewModel::class.java)) {
            return VideoGameViewModel(videoGameUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}