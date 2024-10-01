package com.janteadebowale.datacapture.auth.presentation.login

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.janteadebowale.datacapture.R
import com.janteadebowale.datacapture.core.presentation.designsystem.component.DCBackground
import com.janteadebowale.datacapture.core.presentation.designsystem.component.DCButton
import com.janteadebowale.datacapture.core.presentation.designsystem.component.DCMessageDialog
import com.janteadebowale.datacapture.core.presentation.designsystem.component.DCPasswordField
import com.janteadebowale.datacapture.core.presentation.designsystem.component.DCScreenPreview
import com.janteadebowale.datacapture.core.presentation.designsystem.component.DCTextButton
import com.janteadebowale.datacapture.core.presentation.designsystem.component.DCTextField
import com.janteadebowale.datacapture.core.presentation.designsystem.component.DCVerticalLogo
import com.janteadebowale.datacapture.core.presentation.designsystem.component.DialogState
import com.janteadebowale.datacapture.core.presentation.designsystem.component.DialogType
import com.janteadebowale.datacapture.core.presentation.designsystem.theme.DataCaptureTheme
import com.janteadebowale.datacapture.core.presentation.ui.ToEventResult
import org.koin.androidx.compose.koinViewModel

/**********************************************************
2024 Copyright (C), JTA
https://www.janteadebowale.com | jante.adebowale@gmail.com
 **********************************************************
 * Author    : Jante Adebowale
 * Project   : DataCapture
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 **********************************************************/

@Composable
fun LoginRoute(
    onNavigateToSignup: () -> Unit,
    onNavigateToHome: () -> Unit,
    loginViewModel: LoginViewModel = koinViewModel<LoginViewModel>(),
) {

    var showAlertDialog by remember { mutableStateOf(false) }
    var dialogState by remember {
        mutableStateOf(DialogState())
    }
    when {
        showAlertDialog -> {
            DCMessageDialog(
                message = dialogState.message,
                type = dialogState.type,
                navigateOnDismiss = dialogState.navigateBack
            ) {
                showAlertDialog = false

            }
        }
    }

    LoginScreen(loginViewModel.uiState, onAction = {
        when (it) {
            LoginAction.Signup -> {
                onNavigateToSignup()
            }

            else -> {
                loginViewModel.onAction(it)
            }
        }
    })
    loginViewModel.loginChannel.ToEventResult (lifecycleOwner = LocalLifecycleOwner.current) { loginEvents ->
        when (loginEvents) {
            is LoginEvent.LoginFailed -> {
                dialogState = DialogState(
                    message = loginEvents.errorMessage,
                    type = DialogType.Error,
                    navigateBack = false
                )
                showAlertDialog = true
            }

            LoginEvent.LoginSuccess -> {
                onNavigateToHome()
            }
        }
    }
}

@Composable
fun LoginScreen(
    uiState: LoginUiState,
    onAction: (LoginAction) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val passwordFocusRequester = remember {
        FocusRequester()
    }
    DCBackground(
        modifier = Modifier
            .padding(vertical = 50.dp, horizontal = 16.dp)
    ) {
        DCVerticalLogo()
        Spacer(modifier = Modifier.height(20.dp))
        DCTextField(
            state = uiState.email,
            hint = stringResource(id = R.string.enter_email),
            title = stringResource(id = R.string.email),
            error = "",
            leadingIcon = Icons.Outlined.Email,
            trailingIcon = if (uiState.isValidEmail) Icons.Outlined.Check else null,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Email
            ),
            onKeyBoardAction = {
                passwordFocusRequester.requestFocus()
            },
            modifier = Modifier
                .fillMaxWidth()
        )

        DCPasswordField(
            state = uiState.password,
            hint = stringResource(id = R.string.enter_hint),
            title = stringResource(id = R.string.password),
            leadingIcon = Icons.Outlined.Lock,
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(passwordFocusRequester),
            isPasswordVisible = uiState.isPasswordVisible,
            onTogglePasswordVisibility = {
                onAction(LoginAction.TogglePasswordVisibility)
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password
            ),
            onKeyBoardAction = {
                focusManager.clearFocus()
            },
        )


        DCButton(
            text = stringResource(id = R.string.login),
            enableState = uiState.canProceed,
            isLoading = uiState.isLoading,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (!uiState.isLoading) {
                focusManager.clearFocus()
                onAction(LoginAction.Login)
            }
        }

        DCTextButton(
            text = stringResource(id = R.string.signup),
            modifier = Modifier.align(Alignment.End)
        ) {
            onAction(LoginAction.Signup)
        }


    }
}

@DCScreenPreview
@Composable
fun PreviewLoginScreen() {
    DataCaptureTheme {
        LoginScreen(uiState =
        LoginUiState(), onAction = {})
    }
}

