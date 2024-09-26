package com.janteadebowale.datacapture.home.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

/**********************************************************
2024 Copyright (C), JTA
https://www.janteadebowale.com | jante.adebowale@gmail.com
 **********************************************************
 * Author    : Jante Adebowale
 * Project   : DataCapture
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 **********************************************************/
class HomeViewModel : ViewModel() {

    var uiState by mutableStateOf(HomeUiState())
        private set

    fun onAction(homeAction: HomeAction){
        when(homeAction){
            is HomeAction.FabToggle -> {
                uiState = uiState.copy(isFabExpanded = homeAction.expand)
            }
            else -> {}
        }
    }
}


sealed interface HomeAction {
    data object Logout : HomeAction
    data object Capture : HomeAction
    data object Setting : HomeAction
    data class FabToggle(val expand: Boolean) : HomeAction
}

