package com.janteadebowale.datacapture.auth.domain.model

data class SignupRequest(
    val email: String,
    val firstname: String,
    val lastname: String,
    val password: String
)