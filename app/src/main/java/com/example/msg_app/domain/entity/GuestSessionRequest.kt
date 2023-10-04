package com.example.msg_app.domain.entity

import com.google.gson.annotations.SerializedName

data class GuestSessionRequest(
    val success: Boolean,
    @SerializedName("guest_session_id")
    val id: String,
    @SerializedName("expires_at")
    val expiresAt: String
)