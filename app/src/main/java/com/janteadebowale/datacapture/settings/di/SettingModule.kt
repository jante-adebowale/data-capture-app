package com.janteadebowale.datacapture.settings.di

import com.janteadebowale.datacapture.settings.presentation.SettingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import androidx.lifecycle.viewmodel.compose.viewModel as viewModel1

/**********************************************************
2024 Copyright (C), JTA
https://www.janteadebowale.com | jante.adebowale@gmail.com
 **********************************************************
 * Author    : Jante Adebowale
 * Project   : DataCapture
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 **********************************************************/

val settingModule = module {
//    viewModel { SettingViewModel(get(),get(),get())}
    viewModelOf(::SettingViewModel)
}