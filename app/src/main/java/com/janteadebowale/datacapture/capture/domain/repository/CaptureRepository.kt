package com.janteadebowale.datacapture.capture.domain.repository

import com.janteadebowale.datacapture.capture.domain.model.Capture
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
interface CaptureRepository {
    suspend fun uploadCapture(captureId:String):DataResult<Unit>

    suspend fun saveCapture(capture: Capture):DataResult<Unit>

}