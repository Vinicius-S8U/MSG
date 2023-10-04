package com.example.msg_app.data.handler

import com.example.msg_app.data.handler.exception.ClientException
import com.example.msg_app.data.handler.exception.ServerException
import com.example.msg_app.data.handler.exception.UnknownResponseException
import com.example.msg_app.data.handler.wrapper.ResultWrapper
import retrofit2.HttpException

suspend fun <T> requestHandler(function: suspend () -> T): ResultWrapper<T> {
    return try {
        ResultWrapper.Success(function())
    } catch (e: HttpException) {
        ResultWrapper.Error(
            when (e.code()) {
                in 400..499 -> ClientException(code = e.code(), message = e.message)
                in 500..599 -> ServerException(code = e.code(), message = e.message)
                else -> UnknownResponseException(code = e.code(), message = e.message)
            }
        )
    }
}