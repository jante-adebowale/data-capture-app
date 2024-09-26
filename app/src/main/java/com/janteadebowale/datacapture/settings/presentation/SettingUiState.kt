package com.janteadebowale.datacapture.settings.presentation

import com.janteadebowale.datacapture.core.domain.model.ThemeConfig
import com.janteadebowale.datacapture.core.domain.model.User

/**********************************************************
2024 Copyright (C), JTA
https://www.janteadebowale.com | jante.adebowale@gmail.com
 **********************************************************
 * Author    : Jante Adebowale
 * Project   : DataCapture
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 **********************************************************/
data class SettingUiState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val currentTheme : ThemeConfig? = null
)
