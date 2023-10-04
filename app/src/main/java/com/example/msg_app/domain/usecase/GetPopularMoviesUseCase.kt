package com.example.msg_app.domain.usecase

import com.example.msg_app.data.handler.wrapper.ResultWrapper
import com.example.msg_app.data.repository.TMDBRepository
import com.example.msg_app.domain.entity.MovieResponse
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val repository: TMDBRepository
) {

    suspend operator fun invoke(accessKey: String): ResultWrapper<MovieResponse> =
        repository.getPopularMovies(accessKey)
}