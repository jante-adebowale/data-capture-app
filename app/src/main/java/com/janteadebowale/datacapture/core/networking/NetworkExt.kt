package com.janteadebowale.datacapture.core.networking

import com.janteadebowale.datacapture.core.common.DataError
import com.janteadebowale.datacapture.core.common.DataResult
import okio.IOException
import retrofit2.HttpException
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import kotlin.coroutines.cancellation.CancellationException

/**********************************************************
2024 Copyright (C), JTA
https://www.janteadebowale.com | jante.adebowale@gmail.com
 **********************************************************
 * Author    : Jante Adebowale
 * Project   : DataCapture
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 **********************************************************/

object ApiEndpoints {
    const val BASE_URL = "http://192.168.102.108:8080/api/"
    const val AUTH_URL = "auth"
    const val SIGNUP_URL = "signup"

    const val AUTH_REFRESH_URL = "auth/refresh-token"

    const val AUTH_PATH = "/api/auth"
    const val SIGNUP_PATH = "/api/signup"
    const val TOKEN_REFRESH_PATH = "/api/auth/refresh-token"
}



inline fun <reified T> safeNetworkCall(executeRequest: () -> Response<T>): DataResult<T> {
    val response = try {
        executeRequest()
    } catch (ioException: IOException) {
        return DataResult.Failure(DataError.Network.UNSTABLE_CONNECTION)
    } catch (socketTime: SocketTimeoutException) {
        return DataResult.Failure(DataError.Network.SOCKET_TIME_OUT)
    } catch (socketTime: ConnectException) {
        return DataResult.Failure(DataError.Network.CONNECT_TIME_OUT)
    } catch (http: HttpException) {
        return DataResult.Failure(DataError.Network.UNAUTHORIZED)
    } catch (exception: Exception) {
        exception.printStackTrace()
        if (exception is CancellationException) throw exception
        return DataResult.Failure(DataError.Network.PROCESS_FAILED)
    }

    return if (response.isSuccessful) {
        return DataResult.Success(response.body()!!)
    } else {
        val code =  response.code().toString()
        when (response.code()) {
            401 -> DataResult.Failure(DataError.Network.UNAUTHORIZED,code)
            400 -> DataResult.Failure(DataError.Network.BAD_REQUEST,code)
            403 -> DataResult.Failure(DataError.Network.FORBIDDEN,code)
            409 -> DataResult.Failure(DataError.Network.CONFLICT,code)
            in 500..599 -> DataResult.Failure(DataError.Network.NO_INTERNET,code)
            else -> DataResult.Failure(DataError.Network.HTTP_ERROR)
        }
    }

}
