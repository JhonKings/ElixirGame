package com.example.elixirgame.presentation.viewmodel.datailvg

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.elixirgame.data.response.VideoGameDetailResponse
import com.example.elixirgame.domain.VideoGameUseCase
import kotlinx.coroutines.launch

class DetailViewModel(private val userCase: VideoGameUseCase): ViewModel() {

    private val _videoGameDetail = MutableLiveData<VideoGameDetailResponse>()

            val videoGameDetailLV: MutableLiveData<VideoGameDetailResponse>
                get() = _videoGameDetail

    fun getDetailVideoGameById(idVideoGame: Long){
        viewModelScope.launch {
            val videoGame = userCase.getVideoGameByIdOnStock(idVideoGame)
            _videoGameDetail.value = videoGame
        }
    }
}