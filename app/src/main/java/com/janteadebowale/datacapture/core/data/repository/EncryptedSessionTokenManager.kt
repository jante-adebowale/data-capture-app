package com.janteadebowale.datacapture.core.data.repository

import android.content.SharedPreferences
import com.janteadebowale.datacapture.core.domain.model.AuthToken
import com.janteadebowale.datacapture.core.domain.repository.SessionTokenManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**********************************************************
2024 Copyright (C), JTA
https://www.janteadebowale.com | jante.adebowale@gmail.com
 **********************************************************
 * Author    : Jante Adebowale
 * Project   : DataCapture
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 **********************************************************/
class EncryptedSessionTokenManager(
    private val sharedPreferences: SharedPreferences,
) : SessionTokenManager {

    override suspend fun set(authToken: AuthToken?) {
        withContext(Dispatchers.IO) {
            val json = Json.encodeToString(authToken)
            sharedPreferences.edit().putString(AUTH_TOKEN_KEY, json).commit()
        }
    }

    override suspend fun get(): AuthToken? {
        return withContext(Dispatchers.IO) {
            val value = sharedPreferences.getString(AUTH_TOKEN_KEY,null)
            val returnAuthValue = value?.let {
                Json.decodeFromString<AuthToken>(it)
            }
            return@withContext returnAuthValue
        }
    }

    override suspend fun clear() {
        withContext(Dispatchers.IO){
            sharedPreferences.edit().remove(AUTH_TOKEN_KEY).commit()
        }
    }

    companion object {
        const val AUTH_TOKEN_KEY = "AUTH_TOKEN_KEY"
    }

}