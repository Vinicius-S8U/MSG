package com.example.msg_app.data.repository

import com.example.msg_app.data.api.TmdbApi
import com.example.msg_app.data.handler.requestHandler
import com.example.msg_app.data.handler.wrapper.ResultWrapper
import com.example.msg_app.domain.entity.GuestSessionRequest
import com.example.msg_app.domain.entity.MovieResponse
import javax.inject.Inject

class TMDBRepository @Inject constructor(
    private val api: TmdbApi
) {

    suspend fun createGuestSession(accessKey: String): ResultWrapper<GuestSessionRequest> {
        return requestHandler {
            api.createGuestSession(accessKey)
        }
    }

    suspend fun getPopularMovies(accessKey: String):ResultWrapper<MovieResponse>{
        return requestHandler {
            api.getPopularMoviesList(accessKey)
        }
    }
}