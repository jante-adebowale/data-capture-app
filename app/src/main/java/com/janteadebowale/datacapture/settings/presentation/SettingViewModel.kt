package com.janteadebowale.datacapture.settings.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.janteadebowale.datacapture.core.database.dao.UserDao
import com.janteadebowale.datacapture.core.database.model.USER_ID
import com.janteadebowale.datacapture.core.domain.Constants
import com.janteadebowale.datacapture.core.domain.model.ThemeConfig
import com.janteadebowale.datacapture.core.domain.repository.SessionTokenManager
import com.janteadebowale.datacapture.core.domain.repository.UserDataManager
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlin.math.log

/**********************************************************
2024 Copyright (C), JTA
https://www.janteadebowale.com | jante.adebowale@gmail.com
 **********************************************************
 * Author    : Jante Adebowale
 * Project   : DataCapture
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 **********************************************************/
class SettingViewModel(
    private val userDao: UserDao,
    private val userDataManager: UserDataManager,
    private val sessionTokenManager: SessionTokenManager
) : ViewModel() {

    var uiState by mutableStateOf(SettingUiState())
        private set

    private val _settingChannel = Channel<SettingEvent>()
    val settingChannelFlow = _settingChannel.receiveAsFlow()

    init {
        userDataManager.getUserTheme().onEach {
            uiState = uiState.copy(currentTheme = it)
        }.launchIn(viewModelScope)
        viewModelScope.launch {
            uiState = uiState.copy(user = userDao.getUser(USER_ID))
        }
    }

    fun onAction(settingAction: SettingAction) {
        when (settingAction) {
            is SettingAction.ChangeTheme -> {
                setTheme(settingAction.selectedTheme)
            }
             SettingAction.Logout ->{
                 logout()
             }

            else -> {}
        }
    }

    private fun setTheme(selectedTheme: ThemeConfig){
        viewModelScope.launch {
            userDataManager.setUserTheme(selectedTheme)
        }
    }

    private fun logout(){
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)
            delay(Constants.DELAY)
            sessionTokenManager.clear()
            uiState = uiState.copy(isLoading = false)

            _settingChannel.send(SettingEvent.Logout)
        }
    }
}


sealed interface SettingAction {
    data class ChangeTheme(val selectedTheme: ThemeConfig) : SettingAction
    data object ChangePassword : SettingAction
    data object Logout : SettingAction
    data object NavigateBack : SettingAction
}

sealed interface SettingEvent{
    data object Logout : SettingEvent
}

