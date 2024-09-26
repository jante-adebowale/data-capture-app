package com.janteadebowale.datacapture.auth.domain.model

data class LoginResponse(
    val data: LoginData,
    val message: String,
    val success: Boolean
)