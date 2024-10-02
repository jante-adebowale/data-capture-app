package com.janteadebowale.datacapture.capture.data.local

import android.database.sqlite.SQLiteFullException
import com.janteadebowale.datacapture.core.common.DataError
import com.janteadebowale.datacapture.core.common.DataResult
import com.janteadebowale.datacapture.core.database.dao.CaptureDao
import com.janteadebowale.datacapture.core.database.model.CaptureEntity
import com.janteadebowale.datacapture.capture.domain.repository.LocalCaptureDataSource
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
class RoomLocalCapture(
    private val captureDao: CaptureDao
) : LocalCaptureDataSource {
    override suspend fun saveCapture(captureEntity: CaptureEntity): DataResult<Unit> {
        try {
            captureDao.insertCapture(captureEntity = captureEntity)
            return DataResult.Success(Unit)
        } catch (diskFullException: SQLiteFullException) {
            return DataResult.Failure(DataError.Local.WRITE)
        } catch (exception: Exception) {
            return DataResult.Failure(DataError.DynamicError("Error: Failed to save capture"))
        }
    }

    override suspend fun markCaptureAsUpload(captureId: String) {
        captureDao.markAsUploaded(captureId)

    }

    override suspend fun removeCaptureAsRecent(captureId: String) {
        captureDao.removeAsRecent(captureId)
    }

    override suspend fun getAllPendingCaptures(): List<CaptureEntity> {
        return captureDao.getAllPending()
    }

    override fun getPendingCount(): Flow<Long> {
        return captureDao.getPendingCount()
    }

    override suspend fun deleteOldCapture(): DataResult<Unit> {
        try {
            captureDao.deleteOldCapture()
            return DataResult.Success(Unit)
        } catch (e: Exception) {
            return DataResult.Failure(DataError.Local.WRITE)
        }
    }


}