package com.janteadebowale.datacapture.capture.data

import com.janteadebowale.datacapture.capture.domain.model.Capture
import com.janteadebowale.datacapture.capture.domain.repository.CaptureRepository
import com.janteadebowale.datacapture.capture.domain.repository.LocalCaptureDataSource
import com.janteadebowale.datacapture.capture.domain.repository.NetworkCaptureDatasource
import com.janteadebowale.datacapture.core.common.DataResult

/**********************************************************
2024 Copyright (C), JTA
https://www.janteadebowale.com | jante.adebowale@gmail.com
 **********************************************************
 * Author    : Jante Adebowale
 * Project   : DataCapture
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 **********************************************************/
class OfflineFirstCaptureRepository(
    private val localCaptureDatasource: LocalCaptureDataSource,
    private val networkCapture: NetworkCaptureDatasource
) : CaptureRepository {
    override suspend fun uploadCapture(captureId: String): DataResult<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun saveCapture(capture: Capture): DataResult<Unit> {
        TODO("Not yet implemented")
    }

}