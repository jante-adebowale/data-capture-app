package com.janteadebowale.datacapture.auth.domain.repository

import com.janteadebowale.datacapture.auth.domain.model.LoginRequest
import com.janteadebowale.datacapture.auth.domain.model.SignupRequest
import com.janteadebowale.datacapture.core.common.DataResult

/**********************************************************
2024 Copyright (C), JTA
https://www.janteadebowale.com | jante.adebowale@gmail.com
 **********************************************************
 * Author    : Jante Adebowale
 * Project   : DataCapture
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 **********************************************************/
interface AuthRepository {
   suspend fun login(loginRequest: LoginRequest) : DataResult<Unit>
   suspend fun signup(signupRequest: SignupRequest) : DataResult<Unit>
}