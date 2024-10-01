package com.janteadebowale.datacapture.auth.data

import com.janteadebowale.datacapture.auth.domain.model.LoginRequest
import com.janteadebowale.datacapture.auth.domain.model.LoginResponse
import com.janteadebowale.datacapture.auth.domain.model.SignupRequest
import com.janteadebowale.datacapture.auth.domain.model.SignupResponse
import com.janteadebowale.datacapture.auth.domain.repository.AuthRepository
import com.janteadebowale.datacapture.core.common.DataError
import com.janteadebowale.datacapture.core.common.DataResult
import com.janteadebowale.datacapture.core.database.dao.UserDao
import com.janteadebowale.datacapture.core.database.model.USER_ID
import com.janteadebowale.datacapture.core.database.model.UserEntity
import com.janteadebowale.datacapture.core.domain.model.AuthToken
import com.janteadebowale.datacapture.core.domain.repository.SessionTokenManager
import com.janteadebowale.datacapture.core.domain.repository.UserDataManager
import com.janteadebowale.datacapture.core.networking.ApiEndpoints
import com.janteadebowale.datacapture.core.networking.safeNetworkCall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
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

interface AuthNetworkApi {
    @POST(ApiEndpoints.AUTH_URL)
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST(ApiEndpoints.SIGNUP_URL)
    suspend fun signup(@Body signupRequest: SignupRequest): Response<SignupResponse>
}


class AuthRepositoryImpl(
    private val authNetworkApi: AuthNetworkApi,
    private val applicationScope: CoroutineScope,
    private val sessionTokenManager: SessionTokenManager,
    private val userDao: UserDao,
    private val userDataManager: UserDataManager
) : AuthRepository {
    override suspend fun login(loginRequest: LoginRequest): DataResult<Unit> {
        val result = safeNetworkCall {
            authNetworkApi.login(loginRequest)
        }
        if (result.isFailure()) {
            return DataResult.Failure(result.getError())
        }

       return when {
            result.getSuccessData().success -> {
                val data = result.getSuccessData().data
                applicationScope.launch {
                    userDataManager.saveUsername(data.email)
                }.join()
                applicationScope.launch {
                    sessionTokenManager.set(
                        AuthToken(
                            accessToken = data.accessToken,
                            refreshToken = data.refreshToken,
                            userId = data.id
                        )
                    )
                }.join()
                applicationScope.launch {
                    userDao.upsert(
                        UserEntity(
                            id = USER_ID,
                            userId = data.id,
                            name = data.name,
                            email = data.email
                        )
                    )
                }.join()
                DataResult.Success(Unit)
            }
            else -> {
                DataResult.Failure(DataError.DynamicError(result.getSuccessData().message))
            }
        }

    }

    override suspend fun signup(signupRequest: SignupRequest): DataResult<Unit> {
        val result = safeNetworkCall {
            authNetworkApi.signup(signupRequest)
        }
        if(result.isFailure()){
            return DataResult.Failure(result.getError())
        }
       return when{
            result.getSuccessData().success ->{
                DataResult.Success(Unit)
            }
            else ->{
                DataResult.Failure(DataError.DynamicError(result.getSuccessData().message))
            }
        }

    }
}