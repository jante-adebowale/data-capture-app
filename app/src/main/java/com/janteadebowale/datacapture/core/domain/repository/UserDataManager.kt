package com.janteadebowale.datacapture.core.domain.repository

import com.janteadebowale.datacapture.core.domain.model.ThemeConfig
import kotlinx.coroutines.flow.Flow

/**********************************************************
2024 Copyright (C), JTA
https://www.janteadebowale.com | jante.adebowale@gmail.com
 **********************************************************
 * Author    : Jante Adebowale
 * Project   : DataCapture
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 **********************************************************/
interface UserDataManager {

    suspend fun saveUsername(username: String)

    fun getUsername(): Flow<String>

    suspend fun setUserTheme(themeConfig: ThemeConfig)

    fun getUserTheme():Flow<ThemeConfig>

}