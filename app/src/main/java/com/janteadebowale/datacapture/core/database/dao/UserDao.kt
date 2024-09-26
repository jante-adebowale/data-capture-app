package com.janteadebowale.datacapture.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.janteadebowale.datacapture.core.database.model.UserEntity
import com.janteadebowale.datacapture.core.domain.model.User

/**********************************************************
2024 Copyright (C), JTA
https://www.janteadebowale.com | jante.adebowale@gmail.com
 **********************************************************
 * Author    : Jante Adebowale
 * Project   : DataCapture
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 **********************************************************/
@Dao
interface UserDao {
    @Upsert
    suspend fun upsert(userEntity: UserEntity)

    @Query("SELECT * FROM users WHERE id =:id")
    suspend fun getUser(id: String): User?
}