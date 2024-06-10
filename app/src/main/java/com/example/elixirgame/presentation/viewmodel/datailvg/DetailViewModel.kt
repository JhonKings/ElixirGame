package com.example.elixirgame.presentation.viewmodel.datailvg

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.elixirgame.data.response.VideoGameDetailResponse
import com.example.elixirgame.domain.VideoGameUseCase
import kotlinx.coroutines.launch

class DetailViewModel(private val useCase: VideoGameUseCase): ViewModel() {

    private val _videoGameDetail = MutableLiveData<VideoGameDetailResponse>()

            val videoGameDetailLV: MutableLiveData<VideoGameDetailResponse>
                get() = _videoGameDetail

    fun getDetailVideoGameById(idVideoGame: Long){
        viewModelScope.launch {

            try {
                val videoGame = useCase.getVideoGameByIdOnStock(idVideoGame)
                if (videoGame != null) {
                    _videoGameDetail.value = videoGame
                }else{
                    //Toast.makeText(this, "Video Game Details not found", Toast.LENGTH_SHORT).show()
                    Log.e("Error", "Not Network Connection 2")

                }

            }catch (e: Exception){
                Log.e("MainActivity", "Not network connection")
                _videoGameDetail.value = useCase.getDetailVideoGameFromDB(idVideoGame)
            }




        }
    }

}