package com.janteadebowale.datacapture.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
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

const val USER_ID = "USER_ID"

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val userId: String,
    val email: String,
    val firstname: String,
    val lastname: String,
)

fun UserEntity.toUserModel(): User = User(
    userId = this.id,
    email = this.email,
    firstname = this.firstname,
    lastname = this.lastname
)



