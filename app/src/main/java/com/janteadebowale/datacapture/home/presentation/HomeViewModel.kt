package com.janteadebowale.datacapture.home.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

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

    private val _homeChannel = Channel<HomeEvent>()
    val homeChannel = _homeChannel.receiveAsFlow()

    fun onAction(homeAction: HomeAction){
        when(homeAction){
            is HomeAction.OnFabToggle -> {
                uiState = uiState.copy(isFabExpanded = homeAction.expand)
            }
            else -> {}
        }
    }
}


sealed interface HomeAction {
    data object OnLogout : HomeAction
    data object OnCapture : HomeAction
    data object OnApprovedReport : HomeAction
    data object OnDeclinedReport : HomeAction
    data object OnSetting : HomeAction
    data class OnFabToggle(val expand: Boolean) : HomeAction
}

sealed interface HomeEvent{

}

