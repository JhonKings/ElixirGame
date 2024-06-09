package com.example.elixirgame.data.network.api

import com.example.elixirgame.data.response.VideoGameDetailResponse
import com.example.elixirgame.data.response.VideoGameResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface VideoGameService {

    @GET("games")
    suspend fun getAllVideoGames(): MutableList<VideoGameResponse>

    @GET("gameDetails/{id}")
    suspend fun getVideoGameDetailsById(@Path("id") idVideoGame: Long): VideoGameDetailResponse



}