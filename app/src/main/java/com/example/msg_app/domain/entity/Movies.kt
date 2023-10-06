package com.example.msg_app.domain.entity

import com.google.gson.annotations.SerializedName

data class Movies(
    val id: Int,
    val title: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("backdrop_path")
    val backgroundImg: String,
    val overview: String
)
