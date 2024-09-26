package com.janteadebowale.datacapture.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.janteadebowale.datacapture.core.domain.repository.UserDataManager
import com.janteadebowale.datacapture.core.domain.repository.SessionTokenManager
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

/**********************************************************
2024 Copyright (C), JTA
https://www.janteadebowale.com | jante.adebowale@gmail.com
 **********************************************************
 * Author    : Jante Adebowale
 * Project   : DataCapture
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 **********************************************************/
class MainViewModel(
    private val sessionTokenManager: SessionTokenManager,
    private val userDataManager: UserDataManager
) : ViewModel() {
    var state by mutableStateOf(MainState())
        private set

//    val appearance = localUserManager.getUserTheme().stateIn(
//        viewModelScope,
//        SharingStarted.Eagerly,
//        3
//    )

//    val appearance = localUserManager.getUserTheme().stateIn(
//        viewModelScope,
//        SharingStarted.Eagerly,
//        ThemeConfig.SYSTEM_DEFAULT
//    )

    init {
        viewModelScope.launch {
            state = state.copy(isCheckingAuth = true)

            state = state.copy(isLoggedIn = sessionTokenManager.get() != null)

            userDataManager.getUserTheme().onEach {
                state = state.copy(currentTheme = it)
            }.launchIn(viewModelScope)

            state = state.copy(isCheckingAuth = false)

        }

    }
}