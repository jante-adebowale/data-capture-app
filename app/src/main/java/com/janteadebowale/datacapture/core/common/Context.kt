package com.janteadebowale.datacapture.core.common

import android.content.Context
import android.health.connect.datatypes.units.Length
import android.widget.Toast

/************************************************************
2024 Copyright (C), JTA
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : DataCapture
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 ************************************************************/

  fun Context.showToast(message:String, length:Int = Toast.LENGTH_LONG){
       Toast.makeText(this,message,length).show()
  }