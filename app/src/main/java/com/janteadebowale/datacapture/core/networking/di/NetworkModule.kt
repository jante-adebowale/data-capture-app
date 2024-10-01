package com.janteadebowale.datacapture.core.networking.di

import com.janteadebowale.datacapture.BuildConfig
import com.janteadebowale.datacapture.core.networking.ApiEndpoints
import com.janteadebowale.datacapture.core.networking.ConnectivityManagerNetworkMonitor
import com.janteadebowale.datacapture.core.networking.NetworkMonitor
import com.janteadebowale.datacapture.core.networking.NetworkRequestInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**********************************************************
 2024 Copyright (C), JTA
 https://www.janteadebowale.com | jante.adebowale@gmail.com
 **********************************************************
 * Author    : Jante Adebowale
 * Project   : DataCapture
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 **********************************************************/

fun providesLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
}

//val loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
//    level = HttpLoggingInterceptor.Level.BODY
//}

fun provideHttpClient(
    loggingInterceptor: HttpLoggingInterceptor,
    networkRequestInterceptor: NetworkRequestInterceptor,
): OkHttpClient {
    return OkHttpClient
        .Builder()
        .callTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .apply {
            if(BuildConfig.DEBUG){
                addInterceptor(loggingInterceptor)
            }
        }
        .addInterceptor(networkRequestInterceptor)
        .build()
}

fun provideConverterFactory(): GsonConverterFactory =
    GsonConverterFactory.create()

fun provideRetrofit(
    okHttpClient: OkHttpClient,
    gsonConverterFactory: GsonConverterFactory,
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(ApiEndpoints.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(gsonConverterFactory)
        .build()
}

val networkModule = module {
    single { NetworkRequestInterceptor(get()) }
    single { providesLoggingInterceptor() }
    single { provideHttpClient(get(), get()) }
    single { provideConverterFactory() }
    single { provideRetrofit(get(), get()) }
    single<NetworkMonitor> { ConnectivityManagerNetworkMonitor(get()) }
}