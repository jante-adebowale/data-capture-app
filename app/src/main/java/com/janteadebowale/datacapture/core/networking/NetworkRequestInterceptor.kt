package com.janteadebowale.datacapture.core.networking

import com.janteadebowale.datacapture.core.domain.model.AuthToken
import com.janteadebowale.datacapture.core.domain.repository.SessionTokenManager
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**********************************************************
 2024 Copyright (C), JTA
 https://www.janteadebowale.com | jante.adebowale@gmail.com
 **********************************************************
 * Author    : Jante Adebowale
 * Project   : DataCapture
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 **********************************************************/
class NetworkRequestInterceptor(
    private val sessionTokenManager: SessionTokenManager,
) : Interceptor, KoinComponent {
    private val tokenApiService: TokenApiService by inject()

    companion object{
        const val HEADER_AUTHORIZATION = "Authorization"
        const val TOKEN_TYPE = "Bearer "
        const val UNAUTHORIZED_HTTP_CODE = 401
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        if (!originalRequest.url.toUrl().path.equals(ApiEndpoints.AUTH_PATH)
            && !originalRequest.url.toUrl().path.equals(ApiEndpoints.SIGNUP_PATH)
            && !originalRequest.url.toUrl().path.equals(ApiEndpoints.TOKEN_REFRESH_PATH)
            ) {
            val authToken = runBlocking {
                sessionTokenManager.get()
            }

            val modifiedRequest = originalRequest.newBuilder()
                .addHeader(HEADER_AUTHORIZATION, "$TOKEN_TYPE${authToken?.accessToken ?: ""}")
                .build()

            val response = chain.proceed(modifiedRequest)

            if (response.code == UNAUTHORIZED_HTTP_CODE) {
                val newAccessToken =  refreshAccessToken(authToken)
                val originalRequestWithNewAccessToken = modifiedRequest.newBuilder()
                    .removeHeader(HEADER_AUTHORIZATION)
                    .addHeader(HEADER_AUTHORIZATION, "$TOKEN_TYPE$newAccessToken")
                    .build()
                return chain.proceed(originalRequestWithNewAccessToken)
            }
            return response;
        }
        return chain.proceed(originalRequest)
    }

    private fun refreshAccessToken(authToken: AuthToken?): String {

        val result = runBlocking {
            val tokenResponse = tokenApiService.refreshToken(
                RefreshTokenRequest(
                    refreshToken = authToken!!.refreshToken,
                    userId = authToken!!.userId
                )
            )

            if(tokenResponse.isSuccessful){
                val responseBody = tokenResponse.body()!!
                sessionTokenManager.set(
                    AuthToken(
                        accessToken = responseBody.accessToken,
                        refreshToken = responseBody.refreshToken,
                        userId = authToken!!.userId
                    )
                )
            }
            tokenResponse
        }
        return result.body()?.accessToken ?:""

    }

}