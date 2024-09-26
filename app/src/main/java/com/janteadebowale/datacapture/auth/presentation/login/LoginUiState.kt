package com.janteadebowale.datacapture.auth.presentation.login

import androidx.compose.foundation.text.input.TextFieldState

/**********************************************************
2024 Copyright (C), JTA
https://www.janteadebowale.com | jante.adebowale@gmail.com
 **********************************************************
 * Author    : Jante Adebowale
 * Project   : DataCapture
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 **********************************************************/
data class LoginUiState(
 val isLoading:Boolean = false,
 val canProceed:Boolean = false,
 val isPasswordVisible: Boolean = false,
 val isValidEmail: Boolean = false,
 val email:TextFieldState = TextFieldState(),
 val password:TextFieldState = TextFieldState(),
)
