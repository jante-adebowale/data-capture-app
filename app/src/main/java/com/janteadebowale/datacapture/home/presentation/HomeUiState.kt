package com.janteadebowale.datacapture.home.presentation

/**********************************************************
2024 Copyright (C), JTA
https://www.janteadebowale.com | jante.adebowale@gmail.com
 **********************************************************
 * Author    : Jante Adebowale
 * Project   : DataCapture
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 **********************************************************/
data class HomeUiState(
    val isLoading: Boolean = false,
    val pending: Int = 0,
    val isFabExpanded: Boolean = false,
    val totalCaptured: Int = 0,
    val totalApproved: Int = 0,
    val totalDeclined: Int = 0,
)
