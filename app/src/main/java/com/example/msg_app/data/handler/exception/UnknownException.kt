package com.example.msg_app.data.handler.exception

import java.lang.Exception

class UnknownResponseException(
    override val message: String?,
    val code: Int
) : Exception(message)