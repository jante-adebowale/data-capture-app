package com.janteadebowale.datacapture.auth.domain.model

import com.google.gson.annotations.SerializedName

data class LoginData(
    val id: String,
    val email: String,
    val name: String,
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("refresh_token")
    val refreshToken: String,
)