package com.example.elixirgame.data.repository

import com.example.elixirgame.data.response.VideoGameDetailResponse
import com.example.elixirgame.data.response.VideoGameResponse

interface VideoGameRepository {

    suspend fun fetchGetAllVideoGames(): MutableList<VideoGameResponse>

    suspend fun fetchVideoGameById(idVideoGame: Long): VideoGameDetailResponse


}