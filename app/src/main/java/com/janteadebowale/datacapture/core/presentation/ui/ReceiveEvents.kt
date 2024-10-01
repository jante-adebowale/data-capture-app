package com.janteadebowale.datacapture.core.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

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
fun <T> Flow<T>.ToEventResult(lifecycleOwner: LifecycleOwner, onReceive: (T) -> Unit) {
    LaunchedEffect(this) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            withContext(Dispatchers.Main.immediate) {
                this@ToEventResult.collect(onReceive)
            }
        }
    }
}