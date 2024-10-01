package com.janteadebowale.datacapture

import android.app.Application
import com.janteadebowale.datacapture.auth.di.authModule
import com.janteadebowale.datacapture.core.database.di.databaseModule
import com.janteadebowale.datacapture.core.networking.di.networkModule
import com.janteadebowale.datacapture.core.di.coreModule
import com.janteadebowale.datacapture.home.di.homeModule
import com.janteadebowale.datacapture.main.di.appModule
import com.janteadebowale.datacapture.settings.di.settingModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import java.time.Instant

/**********************************************************
2024 Copyright (C), JTA
https://www.janteadebowale.com | jante.adebowale@gmail.com
 **********************************************************
 * Author    : Jante Adebowale
 * Project   : DataCapture
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 **********************************************************/
class CaptureApplication : Application() {

    val applicationCoroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    override fun onCreate() {
        super.onCreate()


        startKoin {
            androidLogger()
            androidContext(this@CaptureApplication)
            modules (
                appModule,
                coreModule,
                databaseModule,
                networkModule,
                authModule,
                settingModule,
                homeModule
            )

        }
    }

}