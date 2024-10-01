package com.janteadebowale.datacapture.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.janteadebowale.datacapture.core.database.model.CaptureEntity
import kotlinx.coroutines.flow.Flow

/************************************************************
2024 Copyright (C), JTA
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : DataCapture
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 ************************************************************/
@Dao
interface CaptureDao {
   @Insert
   suspend fun insertCapture(captureEntity: CaptureEntity)

   @Query("DELETE  FROM capture_entity WHERE id =:id")
   suspend fun deleteCapture(id:String)

   @Query("SELECT * FROM capture_entity WHERE uploaded =:uploaded ORDER BY time ASC")
   suspend fun getAllPending(uploaded: Boolean = false):List<CaptureEntity>

   @Query("SELECT * FROM capture_entity ORDER BY time DESC LIMIT 4")
   suspend fun getRecentCapture():List<CaptureEntity>

   @Query("SELECT COUNT(*) FROM capture_entity WHERE uploaded =:uploaded")
   fun getPendingCount(uploaded: Boolean = false):Flow<Long>

   @Query("UPDATE capture_entity SET uploaded =:uploaded WHERE id =:id")
   suspend fun markAsUploaded(id:String,uploaded: Boolean = true)

   @Query("UPDATE capture_entity SET showAsRecent =:showAsRecent WHERE id =:id")
   suspend fun removeAsRecent(id:String,showAsRecent: Boolean = true)


}