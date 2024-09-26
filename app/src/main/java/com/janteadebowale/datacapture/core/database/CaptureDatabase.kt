package com.janteadebowale.datacapture.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.janteadebowale.datacapture.core.database.dao.UserDao
import com.janteadebowale.datacapture.core.database.model.UserEntity

/**********************************************************
2024 Copyright (C), JTA
https://www.janteadebowale.com | jante.adebowale@gmail.com
 **********************************************************
 * Author    : Jante Adebowale
 * Project   : DataCapture
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 **********************************************************/
@Database(
    entities = [UserEntity::class],
    version = 1
)
abstract class CaptureDatabase : RoomDatabase() {
    abstract val userDao: UserDao
}