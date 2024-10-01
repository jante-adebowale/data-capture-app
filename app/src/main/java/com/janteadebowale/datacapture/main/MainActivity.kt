package com.janteadebowale.datacapture.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.janteadebowale.datacapture.core.domain.model.ThemeConfig
import com.janteadebowale.datacapture.core.presentation.navigation.DestinationGraph
import com.janteadebowale.datacapture.core.presentation.navigation.MainNavHost
import com.janteadebowale.datacapture.core.presentation.designsystem.theme.DataCaptureTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel by viewModel<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        splashScreen.setKeepOnScreenCondition{
            viewModel.state.isCheckingAuth
        }
        setContent {

            val darkTheme = isDarkTheme(currentTheme = viewModel.state.currentTheme)
            DisposableEffect(darkTheme) {
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.auto(
                        android.graphics.Color.TRANSPARENT,
                        android.graphics.Color.TRANSPARENT,
                    ) { darkTheme }
                )
                onDispose {}
            }

            DataCaptureTheme(
                darkTheme = darkTheme
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    val navHostController = rememberNavController()
                    if (!viewModel.state.isCheckingAuth) {
                        MainNavHost(
                            navHostController,
                            destinationGraph = if (viewModel.state.isLoggedIn) DestinationGraph.Home else DestinationGraph.Auth
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun isDarkTheme(currentTheme: ThemeConfig):Boolean{
    return  when (currentTheme) {
        ThemeConfig.LIGHT -> false
        ThemeConfig.DARK -> true
        ThemeConfig.SYSTEM_DEFAULT -> isSystemInDarkTheme()
    }
}


