package com.janteadebowale.datacapture.core.presentation.navigation

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

sealed interface Screens {
    @Serializable
    data object Login : Screens
    @Serializable
    data object Signup : Screens
    @Serializable
    data object Capture : Screens
    @Serializable
    data object Settings : Screens

    @Serializable
    data object Home : Screens

    @Serializable
    data object Approved : Screens

    @Serializable
    data object Declined : Screens

}