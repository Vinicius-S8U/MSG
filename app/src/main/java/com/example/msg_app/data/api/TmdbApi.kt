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

    @GET("movie/top_rated")
    @Headers("accept: application/json")
    suspend fun getTopRatedMoviesList(@Header("Authorization") accessKey: String): MovieResponse

    @GET("movie/now_playing")
    @Headers("accept: application/json")
    suspend fun getNowPlayingMoviesList(@Header("Authorization") accessKey: String): MovieResponse

    @GET("movie/upcoming")
    @Headers("accept: application/json")
    suspend fun getUpcomingMoviesList(@Header("Authorization") accessKey: String): MovieResponse


    @GET("person/popular")
    @Headers("accept: application/json")
    suspend fun getPopularPeopleList(@Header("Authorization") accessKey: String)
}