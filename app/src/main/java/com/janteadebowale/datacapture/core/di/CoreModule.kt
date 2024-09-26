package com.janteadebowale.datacapture.core.di

import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.janteadebowale.datacapture.core.data.EmailPatternValidator
import com.janteadebowale.datacapture.core.data.repository.EncryptedSessionTokenManager
import com.janteadebowale.datacapture.core.data.repository.UserDataManagerImpl
import com.janteadebowale.datacapture.core.domain.PatternValidator
import com.janteadebowale.datacapture.core.domain.repository.UserDataManager
import com.janteadebowale.datacapture.core.domain.repository.SessionTokenManager
import org.koin.android.ext.koin.androidApplication
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

val coreModule = module {
    single<SharedPreferences> {
        EncryptedSharedPreferences(
            androidApplication(),
            "auth_token_pref",
            MasterKey(androidApplication()),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }


    single<SessionTokenManager> { EncryptedSessionTokenManager(get()) }
    single<UserDataManager> { UserDataManagerImpl(get())}
    single<PatternValidator> { EmailPatternValidator() }


}