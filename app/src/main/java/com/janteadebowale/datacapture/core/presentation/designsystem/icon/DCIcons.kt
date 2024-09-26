package com.janteadebowale.datacapture.core.presentation.designsystem.icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.janteadebowale.datacapture.R

/**********************************************************
2024 Copyright (C), JTA
https://www.janteadebowale.com | jante.adebowale@gmail.com
 **********************************************************
 * Author    : Jante Adebowale
 * Project   : DataCapture
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 **********************************************************/
object DCIcons {
    val add = Icons.Outlined.Add
    val lightLogo = R.drawable.ic_logo_light
    val darkLogo = R.drawable.ic_logo_dark
}

val Light_Logo: ImageVector
    @Composable
    get() = ImageVector.vectorResource(id = R.drawable.ic_logo_light)

val Dark_Logo: ImageVector
    @Composable
    get() = ImageVector.vectorResource(id = R.drawable.ic_logo_dark)