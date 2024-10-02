package com.janteadebowale.datacapture.capture.domain.model

import java.time.ZonedDateTime

/************************************************************
2024 Copyright (C), JTA
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : DataCapture
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 ************************************************************/
data class Capture(
 val id:String,
 val name:String,
 val age: Int,
 val longitude: String,
 val latitude:String,
 val dateTime:ZonedDateTime,
 val uploaded:Boolean,
 val showAsRecent:Boolean,
 val userId:String
)
