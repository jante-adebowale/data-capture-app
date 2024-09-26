package com.janteadebowale.datacapture.core.presentation.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.janteadebowale.datacapture.R
import com.janteadebowale.datacapture.core.presentation.designsystem.theme.Poppins

/**********************************************************
2024 Copyright (C), JTA
https://www.janteadebowale.com | jante.adebowale@gmail.com
 **********************************************************
 * Author    : Jante Adebowale
 * Project   : DataCapture
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 **********************************************************/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DCLogoutDialog(
    showInfoDialog: Boolean = false,
    modifier: Modifier = Modifier,
    icon: (@Composable () -> Unit)? = null,
    title: String? = null,
    info: String,
    onConfirm: () -> Unit = {},
    onCancel: () -> Unit = {},
) {

    val config = LocalConfiguration.current
    val screenWidth = config.screenWidthDp.dp

    if (!showInfoDialog) {
        AlertDialog(
            shape = RoundedCornerShape(8.dp),
            icon = icon,
            title = {
                title?.let {
                    Text(
                        text = title,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontFamily = Poppins,
                        fontSize = 15.sp
                    )
                }

            }, text = {
                Text(text = info)
            }, onDismissRequest = {
                // onCancel()
            }, confirmButton = {
                DCCommonButton(text = stringResource(id = R.string.confirm)) {
                    onConfirm()
                }
            },
            dismissButton = {
                DCCancelButton(text = stringResource(id = R.string.cancel)) {
                    onCancel()
                }
            })
    } else {
        BasicAlertDialog(
            onDismissRequest = {
                onCancel
            },
        ) {

            Box(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(8.dp))
                    .height(250.dp)
                    .width(screenWidth - 40.dp)
                    .background(Color.White)
                    .padding(vertical = 15.dp, horizontal = 20.dp),
                contentAlignment = Alignment.Center
            ) {

                Column(
                    modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    icon?.invoke()
                    Spacer(modifier = Modifier.height(18.dp))
                    Text(
                        text = info,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = Poppins,
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    DCCommonButton(text = stringResource(id = R.string.ok)) {
                        onCancel()
                    }

                }
            }

        }

    }
}