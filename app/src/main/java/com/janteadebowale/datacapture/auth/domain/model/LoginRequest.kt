package com.janteadebowale.datacapture.auth.domain.model

data class LoginRequest(
    val email: String,
    val password: String
)