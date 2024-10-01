package com.janteadebowale.datacapture.auth.domain.model

data class SignupRequest(
    val email: String,
    val name: String,
    val password: String
)