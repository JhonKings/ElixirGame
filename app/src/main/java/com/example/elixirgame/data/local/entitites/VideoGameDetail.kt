package com.example.elixirgame.data.local.entitites

data class VideoGameDetail(
    var idDetail: Long,
    val playtime: Long,
    val platforms: String,
    val genres: String,
    val format: String,
    val price: Double,
    val lastPrice: Double,
    val delivery: Boolean
) {
}