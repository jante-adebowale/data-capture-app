package com.janteadebowale.datacapture.core.data.repository

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.janteadebowale.datacapture.core.data.PreferenceKeys
import com.janteadebowale.datacapture.core.domain.Constants
import com.janteadebowale.datacapture.core.domain.model.ThemeConfig
import com.janteadebowale.datacapture.core.domain.repository.UserDataManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**********************************************************
2024 Copyright (C), JTA
https://www.janteadebowale.com | jante.adebowale@gmail.com
 **********************************************************
 * Author    : Jante Adebowale
 * Project   : DataCapture
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 **********************************************************/
class UserDataManagerImpl(
    private val application: Application,
) : UserDataManager {
    override suspend fun saveUsername(username: String) {
        application.dataStore.edit { usernamePreference ->
            usernamePreference[PreferenceKeys.USERNAME] = username
        }
    }

    override fun getUsername(): Flow<String> {
        return application.dataStore.data.map { usernamePreference ->
            usernamePreference[PreferenceKeys.USERNAME] ?: ""
        }
    }

    override suspend fun setUserTheme(themeConfig: ThemeConfig) {
        application.dataStore.edit { appearancePref ->
            appearancePref[PreferenceKeys.THEME] = themeConfig.name
        }
    }

    override fun getUserTheme(): Flow<ThemeConfig> {
        return application.dataStore.data.map { theme ->
            ThemeConfig.valueOf(theme[PreferenceKeys.THEME] ?: ThemeConfig.SYSTEM_DEFAULT.name)
        }
    }

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Constants.USER_SETTINGS)
}