package com.janteadebowale.datacapture.core.presentation.designsystem.component

import android.media.Image
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.janteadebowale.datacapture.R
import com.janteadebowale.datacapture.core.domain.Constants.POPUP_DELAY
import com.janteadebowale.datacapture.core.presentation.designsystem.theme.DataCaptureTheme
import kotlinx.coroutines.delay

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
fun DCMessageDialog(
    message: String,
    type: DialogType,
    navigateOnDismiss:Boolean = false,
    onDismissRequest: (Boolean) -> Unit,
) {
    Dialog(properties = DialogProperties(
        dismissOnClickOutside = false
    ), onDismissRequest = {

    }) {

        LaunchedEffect(type) {
            delay(POPUP_DELAY)
            onDismissRequest(navigateOnDismiss)
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(20.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.size(45.dp),
                    painter = painterResource(id = type.image),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = message,
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .wrapContentSize(Alignment.Center),
                    textAlign = TextAlign.Center,
                )
            }
        }


    }
}


enum class DialogType(@DrawableRes val image: Int, val color: Color) {
//    Success(Icons.Filled.Check, Color.Green),
//    Error(Icons.Filled.Cancel, Color.Red),
//    Info(Icons.Filled.Info, Color.Yellow),

    Success(R.drawable.success, Color.Green),
    Error(R.drawable.error, Color.Red),
    Info(R.drawable.error, Color.Yellow),
}

data class DialogState(
    val message: String = "",
    val type: DialogType = DialogType.Success,
    val navigateBack: Boolean = true,
)


@DCScreenPreview
@Composable
fun PreviewMessageDialog() {
    DataCaptureTheme {
        DCMessageDialog(message = "Message goes in here!!!", type = DialogType.Error) {

        }
    }
}
