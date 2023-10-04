package com.example.msg_app.data.api

import com.example.msg_app.domain.entity.GuestSessionRequest
import com.example.msg_app.domain.entity.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers


interface TmdbApi {
    @GET("authentication/guest_session/new")
    @Headers("accept: application/json")
    suspend fun createGuestSession(@Header("Authorization") accessKey: String): GuestSessionRequest

    @GET("movie/popular")
    @Headers("accept: application/json")
    suspend fun getPopularMoviesList(@Header("Authorization") accessKey: String): MovieResponse
}