package com.janteadebowale.datacapture.auth.presentation.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.janteadebowale.datacapture.auth.domain.UserDataValidator
import com.janteadebowale.datacapture.auth.domain.model.SignupRequest
import com.janteadebowale.datacapture.auth.domain.repository.AuthRepository
import com.janteadebowale.datacapture.core.common.toErrorMessage
import com.janteadebowale.datacapture.core.domain.Constants
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
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
class SignupViewModel(
    private val userDataValidator: UserDataValidator,
    private val authRepository: AuthRepository
) : ViewModel() {

    var uiState by mutableStateOf(SignupUiState())
        private set

    private val _signUpChannel = Channel<SignupEvent>()
    val signUpChannel = _signUpChannel.receiveAsFlow()

    init {
        val nameFlow = snapshotFlow {
            uiState.name.text
        }

        val emailFlow = snapshotFlow {
            uiState.email.text
        }
        val passwordFlow = snapshotFlow {
            uiState.password.text
        }
        combine(nameFlow,emailFlow,passwordFlow){ name, email, password ->
              uiState = uiState.copy(canProceed = name.toString().isNotEmpty()
                      && userDataValidator.validateEmail(email.toString())
                      && userDataValidator.validatePassword(password.toString()))
        }.launchIn(viewModelScope)
    }

    fun onAction(signupAction: SignupAction) {
        when (signupAction) {
            SignupAction.Signup -> {
                signUp()
            }

            SignupAction.TogglePasswordVisibility -> {
                uiState = uiState.copy(isPasswordVisible = !uiState.isPasswordVisible)
            }

            else -> {}
        }
    }

    private fun signUp() {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)
            delay(Constants.DELAY)
            val result = authRepository.signup(
                SignupRequest(
                    email = uiState.email.text.toString().trim(),
                    name = uiState.name.text.toString(),
                    password = uiState.password.text.toString()
                )
            )
            uiState = uiState.copy(isLoading = false)

            if(result.isSuccess()){
                 _signUpChannel.send(SignupEvent.SignupSuccess)
            }else{
                _signUpChannel.send(SignupEvent.SignupFailure(result.getError().toErrorMessage()))
            }
        }
    }
}


sealed interface SignupAction {
    data object NavigateToLogin : SignupAction
    data object Signup : SignupAction
    data object TogglePasswordVisibility : SignupAction
}


sealed interface SignupEvent {
    data object SignupSuccess : SignupEvent
    data class SignupFailure(val errorMessage:String) : SignupEvent
}