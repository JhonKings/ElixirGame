package com.example.elixirgame.data.repository

import android.app.Service
import com.example.elixirgame.data.network.api.VideoGameService
import com.example.elixirgame.data.response.VideoGameDetailResponse
import com.example.elixirgame.data.response.VideoGameResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VideoGameImpl(private val apiService: VideoGameService): VideoGameRepository {

    override suspend fun fetchGetAllVideoGames(): MutableList<VideoGameResponse> {
        return withContext(Dispatchers.IO){
            val response = apiService.getAllVideoGames()
            response
        }
    }

    override suspend fun fetchVideoGameById(idVideoGame: Long): VideoGameDetailResponse {
        return withContext(Dispatchers.IO){
            val videoGameDetail = apiService.getVideoGameDetailsById(idVideoGame)
            videoGameDetail
        }
    }


}