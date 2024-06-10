package com.example.elixirgame.presentation.view.listvg

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.elixirgame.data.local.database.AppDataBase
import com.example.elixirgame.data.network.api.VideoGameService
import com.example.elixirgame.data.network.retrofit.RetrofitHelper
import com.example.elixirgame.data.repository.VideoGameImpl
import com.example.elixirgame.databinding.ActivityMainBinding
import com.example.elixirgame.domain.VideoGameUseCase
import com.example.elixirgame.presentation.view.detailvg.DetailActivity
import com.example.elixirgame.presentation.viewmodel.listvg.VideoGameViewModel
import com.example.elixirgame.presentation.viewmodel.listvg.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Capas de la aplicación
        val apiService = RetrofitHelper.getRetrofit().create(VideoGameService::class.java)
       // Log.i("API", "Antes de Database")
        val dataBase = AppDataBase.getDataBase(application)
        //Log.i("API", "Despues de Database")

        val repository = VideoGameImpl(apiService, dataBase.videoGameDAO())
        val useCase = VideoGameUseCase(repository)
        val viewModelFactory = ViewModelFactory(useCase)
        val viewModel = ViewModelProvider(this,viewModelFactory)[VideoGameViewModel::class.java]


        viewModel.getAllVideoGamesFromServer()


        val adapterVideoGame = VideoGameAdapter()
        binding.vgRecycler.adapter = adapterVideoGame
        binding.vgRecycler.layoutManager = LinearLayoutManager(this)

        viewModel.videoGameLV.observe(this) {
            Log.i("GAMES", it.toString())
            adapterVideoGame.videoGames = it
        }

        adapterVideoGame.onItemClickListener = {
            val idVideoGame = it.id
            val nombreVideoGame = it.name
            //Toast.makeText(this, "Video Game: $videoGame $nombreVideoGame", Toast.LENGTH_SHORT).show()
            //va a la segunda actividad o fragmetno
            gotToVideoGameDetailsPage(idVideoGame)

        }



    }

    private fun gotToVideoGameDetailsPage(idVideoGame: Long) {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra("ID_VIDEOGAME", idVideoGame)

        }

        startActivity(intent)
    }
}