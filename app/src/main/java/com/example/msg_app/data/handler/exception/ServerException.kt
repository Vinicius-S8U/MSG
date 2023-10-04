package com.example.msg_app.data.handler.exception

import java.lang.Exception

class ServerException(
    override val message: String?,
    val code: Int
) : Exception(message)