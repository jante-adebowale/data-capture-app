package com.janteadebowale.datacapture.core.data

import androidx.datastore.preferences.core.stringPreferencesKey
import com.janteadebowale.datacapture.core.domain.Constants

/**********************************************************
2024 Copyright (C), JTA
https://www.janteadebowale.com | jante.adebowale@gmail.com
 **********************************************************
 * Author    : Jante Adebowale
 * Project   : DataCapture
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 **********************************************************/
object PreferenceKeys {
 val USERNAME = stringPreferencesKey(name = Constants.USERNAME)
 val THEME = stringPreferencesKey(name = Constants.THEME)
}