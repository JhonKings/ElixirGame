package com.example.elixirgame.domain

import com.example.elixirgame.data.repository.VideoGameImpl
import com.example.elixirgame.data.response.VideoGameDetailResponse
import com.example.elixirgame.data.response.VideoGameResponse

class VideoGameUseCase(private val repository: VideoGameImpl) {

    suspend fun getAllVideoGamesOnStock(): MutableList<VideoGameResponse> {
        return repository.fetchGetAllVideoGames()
    }

    suspend fun getVideoGameByIdOnStock(idVideoGame: Long): VideoGameDetailResponse{
        return repository.fetchVideoGameById(idVideoGame)
    }

}