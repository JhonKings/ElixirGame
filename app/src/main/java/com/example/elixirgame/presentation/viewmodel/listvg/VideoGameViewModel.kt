package com.example.elixirgame.presentation.viewmodel.listvg

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.elixirgame.data.response.VideoGameResponse
import com.example.elixirgame.domain.VideoGameUseCase
import kotlinx.coroutines.launch

class VideoGameViewModel(private val useCase: VideoGameUseCase): ViewModel() {

    private val _videoGameList = MutableLiveData<MutableList<VideoGameResponse>>()
    val videoGameLV
        get() = _videoGameList

    init {
        viewModelScope.launch {
            _videoGameList.value = useCase.getAllVideoGamesOnStock()
        }
    }

}