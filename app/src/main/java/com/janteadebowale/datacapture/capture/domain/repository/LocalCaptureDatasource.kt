package com.janteadebowale.datacapture.capture.domain.repository

import com.janteadebowale.datacapture.core.common.DataResult
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
interface LocalCaptureDataSource {
    suspend fun saveCapture(captureEntity: CaptureEntity): DataResult<Unit>
    suspend fun markCaptureAsUpload(captureId:String)
    suspend fun removeCaptureAsRecent(captureId:String)
    suspend fun getAllPendingCaptures():List<CaptureEntity>
    fun getPendingCount(): Flow<Long>
    suspend fun deleteOldCapture(): DataResult<Unit>
}