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
fun DataError.toErrorMessage(): String {
    return when (this) {
        DataError.Local.WRITE -> DataError.Local.WRITE.message
        DataError.Local.READ -> DataError.Local.READ.message
        DataError.Network.UNAUTHORIZED -> DataError.Network.UNAUTHORIZED.message
        DataError.Network.CONFLICT -> DataError.Network.CONFLICT.message
        DataError.Network.NO_INTERNET -> DataError.Network.NO_INTERNET.message
        DataError.Network.BAD_REQUEST -> DataError.Network.BAD_REQUEST.message
        DataError.Network.SERVER_ERROR -> DataError.Network.SERVER_ERROR.message
        DataError.Network.INVALID_CREDENTIAL -> DataError.Network.INVALID_CREDENTIAL.message
        DataError.Network.FORBIDDEN -> DataError.Network.FORBIDDEN.message
        DataError.Network.CONNECT_TIME_OUT -> DataError.Network.CONNECT_TIME_OUT.message
        DataError.Network.SOCKET_TIME_OUT -> DataError.Network.SOCKET_TIME_OUT.message
        DataError.Network.UNSTABLE_CONNECTION -> DataError.Network.UNSTABLE_CONNECTION.message
        DataError.Network.UNKNOWN -> DataError.Network.UNKNOWN.message
        DataError.Network.HTTP_ERROR -> DataError.Network.HTTP_ERROR.message
        DataError.Network.PROCESS_FAILED -> DataError.Network.PROCESS_FAILED.message
        is DataError.DynamicError -> this.message
    }
}