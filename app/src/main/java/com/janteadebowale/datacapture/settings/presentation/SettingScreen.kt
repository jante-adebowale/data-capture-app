package com.janteadebowale.datacapture.settings.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.janteadebowale.datacapture.R
import com.janteadebowale.datacapture.core.domain.model.ThemeConfig
import com.janteadebowale.datacapture.core.domain.model.User
import com.janteadebowale.datacapture.core.presentation.designsystem.component.DCBackground
import com.janteadebowale.datacapture.core.presentation.designsystem.component.DCCenterTopAppBar
import com.janteadebowale.datacapture.core.presentation.designsystem.component.DCLogoutDialog
import com.janteadebowale.datacapture.core.presentation.designsystem.theme.Poppins
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
fun SettingRoute(
    onNavigateToHome: () -> Unit,
    onLogout: () -> Unit,
    settingViewModel: SettingViewModel = koinViewModel<SettingViewModel>(),
) {

    var showLogoutDialog by remember {
        mutableStateOf(false)
    }
    if (showLogoutDialog) {
        DCLogoutDialog(
            title = stringResource(id = R.string.logout),
            info = stringResource(id = R.string.confirm_logout_message),
            onCancel = {
                showLogoutDialog = false
            },
            onConfirm = {
                showLogoutDialog = false
                settingViewModel.onAction(SettingAction.Logout)
            })
    }

    settingViewModel.settingChannelFlow.ToEventResult(lifecycleOwner = LocalLifecycleOwner.current) {
        when(it){
            SettingEvent.Logout -> {
                onLogout()
            }
        }
    }

    SettingScreen(uiState = settingViewModel.uiState) { settingAction ->
        when (settingAction) {
            SettingAction.NavigateBack -> {
                onNavigateToHome()
            }
            is SettingAction.ChangeTheme ->{
                settingViewModel.onAction(settingAction)
            }
            SettingAction.Logout ->{
                showLogoutDialog = true
            }

            else -> {

            }
        }
    }
}

@Composable
fun SettingScreen(
    uiState: SettingUiState,
    onAction: (SettingAction) -> Unit,
) {
    Scaffold(
        topBar = {
            DCCenterTopAppBar(topAppBarText = stringResource(id = R.string.settings), onNavUp = {
                onAction(SettingAction.NavigateBack)
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
            if (uiState.isLoading) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }
            ProfileView(uiState.user)


            SettingMenu(
                icon = Icons.Filled.Lock,
                mainText = "Change Password",
                subText = "Change your current password"
            ) {
                onAction(SettingAction.ChangePassword)
            }


            SettingMenu(
                icon = Icons.AutoMirrored.Filled.Logout,
                mainText = "Log out",
                subText = "Log out from your Account"
            ) {
                onAction(SettingAction.Logout)
            }

            HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Appearance",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Left
            )
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AppearanceView(currentTheme = uiState.currentTheme!!) {
                    onAction(SettingAction.ChangeTheme(it))
                }
            }


        }
    }
}


@Composable
private fun ProfileView(
    user: User? = null,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(MaterialTheme.colorScheme.surfaceContainerLowest)
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "${user?.name}",
            fontFamily = Poppins,
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )


        Text(
            text = user?.email ?: "",
            fontFamily = Poppins,
            color = Color.Gray,
            fontSize = 10.sp,
            fontWeight = FontWeight.SemiBold,
        )

    }
}

@Composable
private fun SettingMenu(
    modifier: Modifier = Modifier,
    icon: ImageVector, mainText: String, subText: String, onClick: () -> Unit,
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .clickable {
                onClick()
            }
            .background(MaterialTheme.colorScheme.surfaceContainerLowest)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {


        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .clip(shape = MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colorScheme.background)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(8.dp)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))
            Column(
                modifier = Modifier.offset(y = (2).dp)
            ) {
                Text(
                    text = mainText,
                    fontFamily = Poppins,
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                )

                Text(
                    text = subText,
                    fontFamily = Poppins,
                    color = Color.Gray,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.offset(y = (-4).dp)
                )
            }
        }


        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
            contentDescription = "",
            modifier = Modifier.size(16.dp)
        )

    }
}

@Composable
private fun RowScope.AppearanceView(
    currentTheme: ThemeConfig,
    onThemeSelected: (ThemeConfig) -> Unit,
) {
    ThemeView(
        text = stringResource(id = R.string.light),
        selected = currentTheme == ThemeConfig.LIGHT
    ) {
        onThemeSelected(ThemeConfig.LIGHT)
    }

    ThemeView(
        text = stringResource(id = R.string.dark),
        selected = currentTheme == ThemeConfig.DARK
    ) {
        onThemeSelected(ThemeConfig.DARK)
    }

    ThemeView(
        text = stringResource(id = R.string.system_default),
        selected = currentTheme == ThemeConfig.SYSTEM_DEFAULT
    ) {
        onThemeSelected(ThemeConfig.SYSTEM_DEFAULT)
    }
}

@Composable
private fun ThemeView(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Row(
        Modifier
            .wrapContentWidth()
            .selectable(
                selected = selected,
                role = Role.RadioButton,
                onClick = onClick,
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RadioButton(
            selected = selected,
            onClick = null,
        )
        Spacer(Modifier.width(8.dp))
        Text(text)
    }
}