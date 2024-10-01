package com.janteadebowale.datacapture.report.representation.approved

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

/************************************************************
2024 Copyright (C), JTA
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : DataCapture
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 ************************************************************/
class ApprovedViewModel : ViewModel() {

    var uiState by mutableStateOf(ApprovedUiState())
        private set
}