package com.janteadebowale.datacapture.core.presentation.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.janteadebowale.datacapture.R
import com.janteadebowale.datacapture.core.presentation.designsystem.icon.Dark_Logo
import com.janteadebowale.datacapture.core.presentation.designsystem.icon.Light_Logo
import com.janteadebowale.datacapture.core.presentation.designsystem.theme.DataCaptureTheme

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
fun DCVerticalLogo(
    title: String = stringResource(id = R.string.app_title),
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DCLogo(
            modifier = Modifier
                .size(45.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = title,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
fun DCLogo(
    modifier: Modifier = Modifier,
    lightTheme: Boolean = LocalContentColor.current.luminance() < 0.5f,
) {
    Image(
        painter = if(lightTheme) painterResource(id = R.drawable.dark_logo) else painterResource(id = R.drawable.light_logo),
        modifier = modifier,
        contentDescription = "Logo"
    )

}

@DCScreenPreview
@Composable
fun PreviewLogo() {
    DataCaptureTheme {
        DCVerticalLogo()
    }
}