package com.janteadebowale.datacapture.report.representation.rejected

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel

/************************************************************
2024 Copyright (C), JTA
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : DataCapture
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 ************************************************************/

@Composable
fun RejectedRoute(
    rejectedViewModel: RejectedViewModel = koinViewModel<RejectedViewModel>()
) {
    RejectedScreen(rejectedViewModel.uiState)
}

@Composable
fun RejectedScreen(
    uiState: RejectedUiState
) {

}