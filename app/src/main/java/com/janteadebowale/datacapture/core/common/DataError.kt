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
sealed interface DataError {
    enum class Network(val message: String) : DataError {
        UNAUTHORIZED("Invalid username or password"),
        CONFLICT("This account already exists"),
        NO_INTERNET("No Internet connection available"),
        BAD_REQUEST("Bad Request"),
        SERVER_ERROR("Internal Server Error"),
        INVALID_CREDENTIAL("Invalid username or password"),
        FORBIDDEN("Forbidden: Request can not be completed"),
        CONNECT_TIME_OUT("Internet Unavailable."),
        SOCKET_TIME_OUT("Server Unavailable.."),
        UNSTABLE_CONNECTION("Connectivity Error. Check Internet Connection"),
        UNKNOWN("Unknown error occurred!"),
        PROCESS_FAILED("Failed to process request!"),
        HTTP_ERROR("Unsuccessful Server Request")
    }

    enum class Local(val message: String) : DataError {
        WRITE("Failed to read local data"),
        READ("Failed to write local data, please check memory space")
    }

    data class DynamicError(val message: String) : DataError
}