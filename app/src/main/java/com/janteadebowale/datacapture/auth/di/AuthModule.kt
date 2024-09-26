package com.janteadebowale.datacapture.auth.di

import com.janteadebowale.datacapture.auth.data.AuthNetworkApi
import com.janteadebowale.datacapture.auth.data.AuthRepositoryImpl
import com.janteadebowale.datacapture.auth.domain.UserDataValidator
import com.janteadebowale.datacapture.auth.domain.repository.AuthRepository
import com.janteadebowale.datacapture.auth.presentation.login.LoginViewModel
import com.janteadebowale.datacapture.auth.presentation.signup.SignupViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit


/**********************************************************
2024 Copyright (C), JTA
https://www.janteadebowale.com | jante.adebowale@gmail.com
 **********************************************************
 * Author    : Jante Adebowale
 * Project   : DataCapture
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 **********************************************************/

fun providesAuthNetworkApi(retrofit: Retrofit): AuthNetworkApi =
    retrofit.create(AuthNetworkApi::class.java)

val authModule = module {
    single { UserDataValidator(get()) }
    viewModel { LoginViewModel(get(),get(),get()) }
    viewModel { SignupViewModel(get(),get()) }

    single {
        providesAuthNetworkApi(get())
    }
    single<AuthRepository> { AuthRepositoryImpl(get(),get(),get(),get(),get()) }
}