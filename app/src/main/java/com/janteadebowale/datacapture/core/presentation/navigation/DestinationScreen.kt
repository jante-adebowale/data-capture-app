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

sealed interface DestinationScreen {
    @Serializable
    data object Login : DestinationScreen
    @Serializable
    data object Signup : DestinationScreen
    @Serializable
    data object Capture : DestinationScreen
    @Serializable
    data object Settings : DestinationScreen

    @Serializable
    data object Home : DestinationScreen

    @Serializable
    data object Approved : DestinationScreen

    @Serializable
    data object Declined : DestinationScreen

}