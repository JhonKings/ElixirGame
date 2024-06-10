package com.example.elixirgame.presentation.view.detailvg

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.elixirgame.data.local.database.AppDataBase
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
        val dataBase = AppDataBase.getDataBase(application)

        val repository = VideoGameImpl(apiService, dataBase.videoGameDAO())
        val userCase = VideoGameUseCase(repository)
        val viewModelFactory = ViewModelDetailFactory(userCase)
        val viewModel = ViewModelProvider(this, viewModelFactory)[DetailViewModel::class.java]

        viewModel.getDetailVideoGameById(idVideoGame)

        viewModel.videoGameDetailLV.observe(this) {
            //Log.i("DetailAct", it.toString())
            //TODO como tengo los datos, puedo mostrarlos en pantallada (pintarlos)
            with(it){
                bindingDetail.txtNameGame.text = name
                bindingDetail.ratingBar.rating = rating.toFloat()
                bindingDetail.txtReleaseDate.text = released
                bindingDetail.txtGenre.text = genres
                bindingDetail.txtPlataform.text = platforms
                bindingDetail.txtMetacritic.text = metacritic.toString()
                bindingDetail.txtLastPrice.text = lastPrice.toString()
                bindingDetail.txtNewPrice.text = price.toString()

                Picasso
                    .get()
                    .load(it.backgroundImage)
                    .into(bindingDetail.imageView)

                bindingDetail.btnSendEmail.setOnClickListener {
                    sendEmailWithVideoGame(name,id)
                }
            }
        }
    }

    private fun sendEmailWithVideoGame(nameVideoGame: String, idVideoGame: Long) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "message/rfc822"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("reyesn.juan@gmail.com"))
        intent.putExtra(Intent.EXTRA_SUBJECT, "Quiero este videojuego")
        intent.putExtra(Intent.EXTRA_TEXT, "Hola\n, Vi el video juego ${nameVideoGame} " +
                "con cod:${idVideoGame} y me gustaría que me conectaran a mi numero __________\n" +
                "Quedo atento")

        if(intent.resolveActivity(packageManager) != null){
            startActivity(Intent.createChooser(intent, "Enviar correo"))

        }else{
            Toast.makeText(this, "No hay apps para enviar el correo", Toast.LENGTH_SHORT).show()

        }
    }
}