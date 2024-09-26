package com.janteadebowale.datacapture.core.presentation.designsystem.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
fun DCButton(
    modifier: Modifier = Modifier,
    text: String,
    enableState: Boolean = true,
    isLoading: Boolean = false,
    onClick: () -> Unit,
) {

    Button(
        enabled = enableState,
        modifier = modifier.height(intrinsicSize = IntrinsicSize.Min),
        onClick = onClick,
        shape = RoundedCornerShape(size = 6.dp)
    ) {
        Box(
            modifier = Modifier.padding(vertical = 8.dp),
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center) {
                androidx.compose.animation.AnimatedVisibility( modifier = Modifier
                    .align(alignment = Alignment.CenterEnd),visible = isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(20.dp),
                        strokeWidth = 2.dp,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
                Text(
                    text = text,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold).copy(fontSize = 14.sp),
                )
            }
        }

    }
}

@Composable
fun DCCommonButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {

    Button(
        modifier = modifier.height(intrinsicSize = IntrinsicSize.Min),
        onClick = onClick,
        shape = RoundedCornerShape(size = 6.dp)
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold

        )
    }
}

@Composable
fun DCCancelButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = modifier.height(intrinsicSize = IntrinsicSize.Min),
        onClick = onClick,
        shape = RoundedCornerShape(size = 6.dp)
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.SemiBold

        )
    }
}

@Composable
fun DCTextButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    TextButton(modifier = modifier, onClick = onClick) {
        Text(text = text)
    }
}