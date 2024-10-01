package com.janteadebowale.datacapture.core.database.di

import android.app.Application
import androidx.room.Room
import com.janteadebowale.datacapture.core.database.CaptureDatabase
import org.koin.dsl.module

/**********************************************************
2024 Copyright (C), JTA
https://www.janteadebowale.com | jante.adebowale@gmail.com
 **********************************************************
 * Author    : Jante Adebowale
 * Project   : DataCapture
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 **********************************************************/

fun provideDataBase(application: Application): CaptureDatabase = Room.databaseBuilder(
 application, CaptureDatabase::class.java, "data-capture.db"
).build()


val databaseModule = module {
     single { provideDataBase(get()) }
     single { get<CaptureDatabase>().userDao() }
     single { get<CaptureDatabase>().captureDao() }
}