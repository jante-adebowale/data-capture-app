package com.janteadebowale.datacapture.core.networking

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

 /**********************************************************
 2024 Copyright (C), JTA
 https://www.janteadebowale.com | jante.adebowale@gmail.com
 **********************************************************
 * Author    : Jante Adebowale
 * Project   : DataCapture
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 **********************************************************/
interface TokenApiService {
    @POST(ApiEndpoints.AUTH_REFRESH_URL)
    suspend fun refreshToken(@Body refreshTokenRequest: RefreshTokenRequest): Response<RefreshTokenResponse>
}