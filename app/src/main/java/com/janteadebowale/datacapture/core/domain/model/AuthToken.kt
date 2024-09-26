package com.janteadebowale.datacapture.core.domain.model

import kotlinx.serialization.Serializable

/**********************************************************
2024 Copyright (C), JTA
https://www.janteadebowale.com | jante.adebowale@gmail.com
 **********************************************************
 * Author    : Jante Adebowale
 * Project   : DataCapture
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 **********************************************************/

@Serializable
data class AuthToken(
    val accessToken: String,
    val refreshToken: String,
    val userId: String,
)
