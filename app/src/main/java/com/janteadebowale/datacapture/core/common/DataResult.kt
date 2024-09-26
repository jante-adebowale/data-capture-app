package com.janteadebowale.datacapture.core.common

/**********************************************************
 2024 Copyright (C), JTA
 https://www.janteadebowale.com | jante.adebowale@gmail.com
 **********************************************************
 * Author    : Jante Adebowale
 * Project   : DataCapture
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 **********************************************************/
sealed interface DataResult<out T> {

    data class Success<T>(val data: T) : DataResult<T>

    data class Failure(val dataError: DataError, val code:String = "0") : DataResult<Nothing>

    fun isSuccess(): Boolean = this is Success

    fun isFailure(): Boolean = this is Failure

    fun getSuccessData() = (this as Success).data

    fun getSuccessDataOrNull(): T? {
        return try {
            (this as Success).data
        } catch (e: Exception) {
            null
        }
    }

    fun getError() = (this as Failure).dataError

    fun getErrorOrNull(): DataError? {
        return try {
            (this as Failure).dataError
        } catch (e: Exception) {
            null
        }
    }
}