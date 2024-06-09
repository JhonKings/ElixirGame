package com.example.elixirgame.presentation.view.detailvg

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.elixirgame.R
import com.example.elixirgame.data.network.api.VideoGameService
import com.example.elixirgame.data.network.retrofit.RetrofitHelper
import com.example.elixirgame.data.repository.VideoGameImpl
import com.example.elixirgame.databinding.ActivityDetailBinding
import com.example.elixirgame.domain.VideoGameUseCase
import com.example.elixirgame.presentation.viewmodel.datailvg.DetailViewModel
import com.example.elixirgame.presentation.viewmodel.datailvg.ViewModelDetailFactory
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {

    private lateinit var bindingDetail: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingDetail = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(bindingDetail.root)

        val idVideoGame = intent.getLongExtra("ID_VIDEOGAME", -1)
        if(idVideoGame == -1L){
            finish()
        }

        val apiService = RetrofitHelper.getRetrofit().create(VideoGameService::class.java)
        val repository = VideoGameImpl(apiService)
        val userCase = VideoGameUseCase(repository)
        val viewModelFactory = ViewModelDetailFactory(userCase)
        val viewModel = ViewModelProvider(this, viewModelFactory)[DetailViewModel::class.java]

        viewModel.getDetailVideoGameById(idVideoGame)

        viewModel.videoGameDetailLV.observe(this) {
            //Log.i("DetailAct", it.toString())
            //TODO como tengo los datos, puedo mostrarlos en pantallada (pintarlos)
            bindingDetail.txtNameGame.text = it.name
            bindingDetail.ratingBar.rating = it.rating.toFloat()
            bindingDetail.txtReleaseDate.text = it.released
            bindingDetail.txtGenre.text = it.genres
            bindingDetail.txtPlataform.text = it.platforms
            bindingDetail.txtMetacritic.text = it.metacritic.toString()
            bindingDetail.txtLastPrice.text = it.lastPrice.toString()
            bindingDetail.txtNewPrice.text = it.price.toString()

           Picasso
               .get()
               .load(it.backgroundImage)
               .into(bindingDetail.imageView)


        }

        //bindingDetail.txtIdGame.text = idVideoGame.toString()

    }
}