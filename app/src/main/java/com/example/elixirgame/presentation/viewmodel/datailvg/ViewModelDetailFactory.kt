package com.example.elixirgame.presentation.viewmodel.datailvg

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.elixirgame.domain.VideoGameUseCase

class ViewModelDetailFactory(private val videoGameUseCase: VideoGameUseCase): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(videoGameUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}