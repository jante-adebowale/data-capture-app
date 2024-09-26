package com.janteadebowale.datacapture.auth.domain

import com.janteadebowale.datacapture.core.domain.PatternValidator

/**********************************************************
2024 Copyright (C), JTA
https://www.janteadebowale.com | jante.adebowale@gmail.com
 **********************************************************
 * Author    : Jante Adebowale
 * Project   : DataCapture
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 **********************************************************/
class UserDataValidator(
    private val patternValidator: PatternValidator,
) {
    fun validateEmail(value: String): Boolean {
        return patternValidator.matches(value)
    }

    fun validatePassword(value: String): Boolean {
        return value.isNotEmpty() && value.length > 4
    }
}