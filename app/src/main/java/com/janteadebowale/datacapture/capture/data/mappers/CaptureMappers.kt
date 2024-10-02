package com.janteadebowale.datacapture.capture.data.mappers

import android.os.Build
import androidx.annotation.RequiresApi
import com.janteadebowale.datacapture.capture.domain.model.Capture
import com.janteadebowale.datacapture.core.database.model.CaptureEntity
import java.time.Instant
import java.time.ZoneId

/************************************************************
2024 Copyright (C), JTA
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : DataCapture
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 ************************************************************/

@RequiresApi(Build.VERSION_CODES.O)
fun CaptureEntity.toModel(): Capture {
    return Capture(
        id = this.id,
        name = this.name,
        age  = this.age,
        longitude = this.longitude,
        latitude = this.latitude,
        dateTime = Instant.parse(dateTime).atZone(ZoneId.of("UTC")),
        uploaded = this.uploaded,
        showAsRecent = this.showAsRecent,
        userId = this.userId
    )
}

@RequiresApi(Build.VERSION_CODES.O)
fun Capture.toEntity(): CaptureEntity {
    return CaptureEntity(
        id = this.id,
        name = this.name,
        age = this.age,
        longitude = this.longitude,
        latitude = this.latitude,
        dateTime = this.dateTime.toInstant().toString(),
        uploaded = this.uploaded,
        showAsRecent = this.showAsRecent,
        userId = this.userId
    )
}