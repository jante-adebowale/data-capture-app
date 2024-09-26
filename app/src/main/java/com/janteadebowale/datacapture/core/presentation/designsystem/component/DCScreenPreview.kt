package com.janteadebowale.datacapture.core.presentation.designsystem.component

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

/**********************************************************
2024 Copyright (C), JTA
https://www.janteadebowale.com | jante.adebowale@gmail.com
 **********************************************************
 * Author    : Jante Adebowale
 * Project   : DataCapture
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 **********************************************************/


@Preview(
 name = "Dark theme preview", showSystemUi = true,
 showBackground = true,
 uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
 name = "Light theme preview", showSystemUi = true,
 showBackground = true,
 uiMode = Configuration.UI_MODE_NIGHT_NO
)
annotation class DCScreenPreview