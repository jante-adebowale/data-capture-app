package com.janteadebowale.datacapture.main

import com.janteadebowale.datacapture.core.domain.model.ThemeConfig

/**********************************************************
2024 Copyright (C), JTA
https://www.janteadebowale.com | jante.adebowale@gmail.com
 **********************************************************
 * Author    : Jante Adebowale
 * Project   : DataCapture
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 **********************************************************/
data class MainState(
    val isLoggedIn: Boolean = false,
    val isCheckingAuth: Boolean = false,
    val currentTheme : ThemeConfig = ThemeConfig.SYSTEM_DEFAULT
)
