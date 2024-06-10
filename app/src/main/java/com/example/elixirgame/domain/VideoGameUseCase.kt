package com.example.elixirgame.domain

import com.example.elixirgame.data.repository.VideoGameImpl
import com.example.elixirgame.data.response.VideoGameDetailResponse
import com.example.elixirgame.data.response.VideoGameResponse

class VideoGameUseCase(private val repository: VideoGameImpl) {

    /**
     * Respositorios usado para API REST
     */
    suspend fun getAllVideoGamesOnStock(): MutableList<VideoGameResponse> {
        return repository.fetchGetAllVideoGames()
    }

    suspend fun getVideoGameByIdOnStock(idVideoGame: Long): VideoGameDetailResponse{
        return repository.fetchVideoGameById(idVideoGame)
    }

    /**
     * Respositorios usado para DB (sin conexion)
     */
    suspend fun saveAllVideoGamesOnDB(videoGames: MutableList<VideoGameResponse>){
        return repository.saveAllVideoGameOnDB(videoGames)
    }

    suspend fun getAllVideoGamesFromDB(): MutableList<VideoGameResponse>{
        return repository.getAllVideoGameFromDB()
    }

    suspend fun saveDetailVideoGameOnDB(videoGameDetail: VideoGameDetailResponse){
        return repository.saveDetailVideoGameOnDB(videoGameDetail)
    }

    suspend fun getDetailVideoGameFromDB(videoGameDetail: Long): VideoGameDetailResponse{
        return repository.getDetailVideoGameFromDB(videoGameDetail)
    }


}