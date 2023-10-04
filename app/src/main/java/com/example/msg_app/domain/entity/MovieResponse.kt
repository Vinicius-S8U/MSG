package com.example.msg_app.domain.entity

data class MovieResponse(
    val page: String,
    val results: List<Movies>,
    val totalPages: Int,
    val totalResults: Int
)