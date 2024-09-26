package com.janteadebowale.datacapture.main.di

import com.janteadebowale.datacapture.CaptureApplication
import com.janteadebowale.datacapture.main.MainViewModel
import kotlinx.coroutines.CoroutineScope
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

/**********************************************************
2024 Copyright (C), JTA
https://www.janteadebowale.com | jante.adebowale@gmail.com
 **********************************************************
 * Author    : Jante Adebowale
 * Project   : DataCapture
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 **********************************************************/

val appModule = module {
    single<CoroutineScope> {
        (androidApplication() as CaptureApplication).applicationCoroutineScope
    }

    viewModelOf(::MainViewModel)
//    viewModel { MainViewModel(get(),get()) }
}