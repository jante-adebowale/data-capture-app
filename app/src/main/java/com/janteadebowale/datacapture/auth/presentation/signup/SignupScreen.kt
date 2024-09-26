package com.janteadebowale.datacapture.auth.presentation.signup

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.janteadebowale.datacapture.R
import com.janteadebowale.datacapture.core.presentation.designsystem.component.DCBackground
import com.janteadebowale.datacapture.core.presentation.designsystem.component.DCButton
import com.janteadebowale.datacapture.core.presentation.designsystem.component.DCCenterTopAppBar
import com.janteadebowale.datacapture.core.presentation.designsystem.component.DCMessageDialog
import com.janteadebowale.datacapture.core.presentation.designsystem.component.DCPasswordField
import com.janteadebowale.datacapture.core.presentation.designsystem.component.DCScreenPreview
import com.janteadebowale.datacapture.core.presentation.designsystem.component.DCTextField
import com.janteadebowale.datacapture.core.presentation.designsystem.component.DialogState
import com.janteadebowale.datacapture.core.presentation.designsystem.component.DialogType
import com.janteadebowale.datacapture.core.presentation.designsystem.theme.DataCaptureTheme
import com.janteadebowale.datacapture.core.presentation.ui.toEventResult
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
fun SignupRoute(
    onNavigateToLogin: () -> Unit,
    signupViewModel: SignupViewModel = koinViewModel<SignupViewModel>(),
) {

    val context = LocalContext.current
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
            ) { navigateBack ->
                showAlertDialog = false
                if (navigateBack) {
                    onNavigateToLogin()
                }
            }
        }
    }

    signupViewModel.signUpChannel.toEventResult(lifecycleOwner = LocalLifecycleOwner.current) { signupEvent ->
        when (signupEvent) {
            is SignupEvent.SignupFailure -> {
                dialogState = DialogState(
                    message = signupEvent.errorMessage,
                    type = DialogType.Error,
                    navigateBack = false
                )
                showAlertDialog = true
            }

            SignupEvent.SignupSuccess -> {
                dialogState = DialogState(
                    message = context.getString(R.string.successful_signup),
                    type = DialogType.Success,
                    navigateBack = true
                )
                showAlertDialog = true
            }
        }
    }

    SignupScreen(uiState = signupViewModel.uiState) { signupAction ->
        when (signupAction) {
            SignupAction.NavigateToLogin -> {
                onNavigateToLogin()
            }

            else -> {
                signupViewModel.onAction(signupAction)
            }

        }
    }

}

@Composable
fun SignupScreen(
    uiState: SignupUiState,
    onAction: (SignupAction) -> Unit,
) {
    val focusManager = LocalFocusManager.current

    val lastnameFocusRequester = remember {
        FocusRequester()
    }
    val emailFocusRequester = remember {
        FocusRequester()
    }

    val passwordFocusRequester = remember {
        FocusRequester()
    }

    Scaffold(
        topBar = {
            DCCenterTopAppBar(topAppBarText = stringResource(id = R.string.signup), onNavUp = {
                onAction(SignupAction.NavigateToLogin)
            })
        }
    ) { paddingValues ->
        DCBackground(
            modifier = Modifier.padding(
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding(),
                start = 16.dp,
                end = 16.dp
            )
        ) {
            DCTextField(
                state = uiState.firstname,
                hint = stringResource(id = R.string.enter_firstname),
                title = stringResource(id = R.string.firstname),
                error = "",
                leadingIcon = Icons.Outlined.PersonOutline,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Unspecified,
                    capitalization = KeyboardCapitalization.Words
                ),
                onKeyBoardAction = {
                    lastnameFocusRequester.requestFocus()
                },
                modifier = Modifier
                    .fillMaxWidth()
            )

            DCTextField(
                state = uiState.lastname,
                hint = stringResource(id = R.string.enter_lastname),
                title = stringResource(id = R.string.lastname),
                error = "",
                leadingIcon = Icons.Outlined.PersonOutline,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Unspecified,
                    capitalization = KeyboardCapitalization.Words
                ),
                onKeyBoardAction = {
                    emailFocusRequester.requestFocus()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(lastnameFocusRequester)
            )

            DCTextField(
                state = uiState.email,
                hint = stringResource(id = R.string.enter_email),
                title = stringResource(id = R.string.email),
                error = "",
                leadingIcon = Icons.Outlined.Email,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Email
                ),
                onKeyBoardAction = {
                    passwordFocusRequester.requestFocus()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(emailFocusRequester)
            )

            DCPasswordField(
                state = uiState.password,
                hint = stringResource(id = R.string.enter_password),
                title = stringResource(id = R.string.password),
                leadingIcon = Icons.Outlined.Lock,
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(passwordFocusRequester),
                isPasswordVisible = uiState.isPasswordVisible,
                onTogglePasswordVisibility = {
                    onAction(SignupAction.TogglePasswordVisibility)
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
                text = stringResource(id = R.string.signup),
                enableState = uiState.canProceed,
                isLoading = uiState.isLoading,
                modifier = Modifier.fillMaxWidth()
            ) {
                if(!uiState.isLoading) {
                    focusManager.clearFocus()
                    onAction(SignupAction.Signup)
                }
            }
        }
    }
}

@DCScreenPreview
@Composable
fun PreviewSignupScreen() {
    DataCaptureTheme {
        SignupScreen(uiState = SignupUiState()) {

        }
    }
}