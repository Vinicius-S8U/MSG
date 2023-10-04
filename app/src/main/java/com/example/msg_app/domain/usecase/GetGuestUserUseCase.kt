package com.example.msg_app.domain.usecase

import com.example.msg_app.data.handler.wrapper.ResultWrapper
import com.example.msg_app.data.repository.TMDBRepository
import com.example.msg_app.domain.entity.GuestSessionRequest
import javax.inject.Inject

class GetGuestUserUseCase
@Inject constructor(
    private val repository: TMDBRepository
) {
    suspend operator fun invoke(accessKey: String): ResultWrapper<GuestSessionRequest> =
        repository.createGuestSession(accessKey)
}