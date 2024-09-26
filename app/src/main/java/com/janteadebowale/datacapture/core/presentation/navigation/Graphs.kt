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

@Serializable
sealed interface Graph{
    @Serializable
    data object Auth : Graph

    @Serializable
    data object Home : Graph
}