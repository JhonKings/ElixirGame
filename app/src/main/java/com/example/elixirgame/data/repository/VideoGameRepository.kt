package com.example.elixirgame.data.repository

import com.example.elixirgame.data.response.VideoGameDetailResponse
import com.example.elixirgame.data.response.VideoGameResponse

interface VideoGameRepository {

    /**
     * Metodos desde API REST
     */

    suspend fun fetchGetAllVideoGames(): MutableList<VideoGameResponse>

    suspend fun fetchVideoGameById(idVideoGame: Long): VideoGameDetailResponse

    /**
     * Metodos desde DB SQLITE  (sin conexion)
     */

    suspend fun saveAllVideoGameOnDB(videoGame: MutableList<VideoGameResponse>)

    suspend fun getAllVideoGameFromDB(): MutableList<VideoGameResponse>

    suspend fun saveDetailVideoGameOnDB(videoGameDetailResponse: VideoGameDetailResponse)

    suspend fun getDetailVideoGameFromDB(idVideoGame: Long): VideoGameDetailResponse




}