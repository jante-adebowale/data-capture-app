package com.janteadebowale.datacapture.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/************************************************************
2024 Copyright (C), JTA
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : DataCapture
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 ************************************************************/
@Entity(tableName = "capture_entity")
data class CaptureEntity(
 @PrimaryKey(autoGenerate = false)
 val id:String,
 val firstname:String,
 val lastname:String,
 val longitude: String,
 val latitude:String,
 val time:Long = System.currentTimeMillis(),
 val uploaded:Boolean,
 val showAsRecent:Boolean,
 val userId:String
)
