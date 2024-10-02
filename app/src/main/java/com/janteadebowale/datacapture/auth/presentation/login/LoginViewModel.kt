package com.janteadebowale.datacapture.auth.presentation.login

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.janteadebowale.datacapture.auth.domain.UserDataValidator
import com.janteadebowale.datacapture.auth.domain.model.LoginRequest
import com.janteadebowale.datacapture.auth.domain.repository.AuthRepository
import com.janteadebowale.datacapture.core.common.toErrorMessage
import com.janteadebowale.datacapture.core.domain.Constants
import com.janteadebowale.datacapture.core.domain.repository.UserDataManager
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
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
class LoginViewModel(
    private val userDataValidator: UserDataValidator,
    private val authRepository: AuthRepository,
    private val userDataManager: UserDataManager
) : ViewModel() {
    var uiState by mutableStateOf(LoginUiState())
        private set

    private val _loginChannel = Channel<LoginEvent>()
    val loginChannel = _loginChannel.receiveAsFlow()

    init {
        val emailFlow = snapshotFlow {
            uiState.email.text
        }

        val passwordFlow = snapshotFlow {
            uiState.password.text
        }

        combine(emailFlow, passwordFlow) { email, password ->
            uiState = uiState.copy(
                canProceed = userDataValidator.validateEmail(email.toString()) && userDataValidator.validatePassword(
                    password.toString()
                )
            )
        }.launchIn(viewModelScope)

        emailFlow.onEach {
            uiState =
                uiState.copy(isValidEmail = userDataValidator.validateEmail(it.toString().trim()))
        }.launchIn(viewModelScope)

        userDataManager.getUsername().onEach {
            uiState = uiState.copy(email = TextFieldState(it))
        }.launchIn(viewModelScope)
    }

    fun onAction(loginAction: LoginAction) {
        when (loginAction) {
            LoginAction.OnLogin -> {
                login()
            }

            LoginAction.OnTogglePasswordVisibility -> {
                uiState = uiState.copy(isPasswordVisible = !uiState.isPasswordVisible)
            }

            else -> TODO()
        }
    }

    private fun login(){
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)
            delay(Constants.DELAY)
             val result =   authRepository.login(
                    LoginRequest(
                        email = uiState.email.text.toString().trim(),
                        password = uiState.password.text.toString()
                    )
                )
            uiState = uiState.copy(isLoading = false)

            if(result.isSuccess()){
                _loginChannel.send(LoginEvent.LoginSuccess)
            }else{
                _loginChannel.send(LoginEvent.LoginFailed(result.getError().toErrorMessage()))
            }
        }
    }

}

sealed interface LoginAction {
    data object OnSignup : LoginAction
    data object OnLogin : LoginAction
    data object OnTogglePasswordVisibility : LoginAction
}

sealed interface LoginEvent {
    data object LoginSuccess : LoginEvent
    data class LoginFailed(val errorMessage:String) : LoginEvent
}