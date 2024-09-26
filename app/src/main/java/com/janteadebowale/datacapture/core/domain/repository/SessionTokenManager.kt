package com.janteadebowale.datacapture.core.domain.repository

import com.janteadebowale.datacapture.core.domain.model.AuthToken

/**********************************************************
2024 Copyright (C), JTA
https://www.janteadebowale.com | jante.adebowale@gmail.com
 **********************************************************
 * Author    : Jante Adebowale
 * Project   : DataCapture
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 **********************************************************/
interface SessionTokenManager {
  suspend fun set(authToken: AuthToken?)

  suspend fun get():AuthToken?

  suspend fun clear()
}