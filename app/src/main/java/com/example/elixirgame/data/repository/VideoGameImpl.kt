package com.example.elixirgame.data.repository

import android.app.Service
import com.example.elixirgame.data.local.dao.VideoGameDao
import com.example.elixirgame.data.network.api.VideoGameService
import com.example.elixirgame.data.response.VideoGameDetailResponse
import com.example.elixirgame.data.response.VideoGameResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VideoGameImpl(
    private val apiService: VideoGameService,
    private val daoDB: VideoGameDao
): VideoGameRepository {

    /**
     * Implementacion de la API REST
     */
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

    /**
     * Implementacion de la BD (sin conexion) a través de un DAO
     */
    override suspend fun saveAllVideoGameOnDB(videoGame: MutableList<VideoGameResponse>) {
        return withContext(Dispatchers.IO){
            daoDB.insertVideoGames(videoGame)
        }
    }

    override suspend fun getAllVideoGameFromDB(): MutableList<VideoGameResponse> {
        return withContext(Dispatchers.IO){
            val response = daoDB.getAllVideoGames()
            response
        }
    }

    override suspend fun saveDetailVideoGameOnDB(videoGameDetailResponse: VideoGameDetailResponse) {
        return withContext(Dispatchers.IO){
            daoDB.insertVideoGameDetail(videoGameDetailResponse)
        }
    }

    override suspend fun getDetailVideoGameFromDB(idVideoGame: Long): VideoGameDetailResponse {
        return withContext(Dispatchers.IO){
            val response = daoDB.getVideoGameDetailById(idVideoGame)
            response
        }

    }


}