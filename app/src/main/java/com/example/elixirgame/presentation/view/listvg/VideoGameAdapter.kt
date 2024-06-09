package com.example.elixirgame.presentation.view.listvg

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.elixirgame.data.response.VideoGameResponse
import com.example.elixirgame.databinding.GameItemBinding
import com.squareup.picasso.Picasso

class VideoGameAdapter: RecyclerView.Adapter<VideoGameAdapter.ViewHolder>(){

    lateinit var onItemClickListener: (VideoGameResponse) -> Unit

    var videoGames = mutableListOf<VideoGameResponse>()
        @SuppressLint("NotifyDataSetChanged")
        set(value){
            field = value
            this.notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = GameItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val videoGame = videoGames[position]
        holder.bindVideoGame(videoGame)
    }

    override fun getItemCount(): Int {
        return videoGames.size
    }

    inner class ViewHolder(private var binding: GameItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindVideoGame(videoGame: VideoGameResponse) {
            binding.imgVideoGame.setImageResource(0)
            Picasso.get()
                .load(videoGame.backgroundImage)
                .centerCrop()
                .fit()
                .into(binding.imgVideoGame)

            binding.txtNameVideoGame.text = videoGame.name
            binding.txtReleasedVideoGame.text = videoGame.released
            binding.txtRatingVideoGame.text = videoGame.rating.toString()

            val layoutParams = binding.root.layoutParams as RecyclerView.LayoutParams
            layoutParams.height = RecyclerView.LayoutParams.WRAP_CONTENT
            binding.root.layoutParams = layoutParams

            clickVideoGameListener(videoGame)
        }

        private fun clickVideoGameListener(videoGame: VideoGameResponse) {
            binding.root.setOnClickListener {
                if(::onItemClickListener.isInitialized){
                    onItemClickListener(videoGame)

                }else{
                    Log.e("VideoGameAdapter", "onItemClickListener no ha inicializado")
                }
            }
        }
    }



}