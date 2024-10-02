package com.janteadebowale.datacapture.core.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.janteadebowale.datacapture.auth.presentation.login.LoginRoute
import com.janteadebowale.datacapture.auth.presentation.signup.SignupRoute
import com.janteadebowale.datacapture.home.presentation.HomeRoute
import com.janteadebowale.datacapture.settings.presentation.SettingRoute

/**********************************************************
2024 Copyright (C), JTA
https://www.janteadebowale.com | jante.adebowale@gmail.com
 **********************************************************
 * Author    : Jante Adebowale
 * Project   : DataCapture
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 **********************************************************/
@Composable
fun MainNavHost(
    navController: NavHostController,
    destinationGraph: DestinationGraph,
) {
    NavHost(navController = navController, startDestination = destinationGraph) {
        authGraph(navController)
        homeGraph(navController)
    }
}

fun NavGraphBuilder.authGraph(navController: NavHostController) {
    navigation<DestinationGraph.Auth>(startDestination = DestinationScreen.Login) {
        composable<DestinationScreen.Login> {
            LoginRoute(onNavigateToSignup = {
                navController.navigate(DestinationScreen.Signup)
            }, onNavigateToHome = {
                navController.navigate(DestinationGraph.Home) {
                    popUpTo(DestinationGraph.Auth) {
                        inclusive = true
                    }
                }
            })
        }

        composable<DestinationScreen.Signup> {
            SignupRoute(onNavigateToLogin = {
                navController.navigate(DestinationScreen.Login)
            })
        }

    }
}

fun NavGraphBuilder.homeGraph(navController: NavHostController) {
    navigation<DestinationGraph.Home>(startDestination = DestinationScreen.Home) {
        composable<DestinationScreen.Home> {
            HomeRoute(
                onNavigateToSettings = {
                    navController.navigate(DestinationScreen.Settings)
                },
                onNavigateToApprovedReport = {
                    navController.navigate(DestinationScreen.Approved)
                },
                onNavigateToCapture = {
                    navController.navigate(DestinationScreen.Capture)
                },
                onNavigateToDeclinedReport = {
                    navController.navigate(DestinationScreen.Declined)
                }
            )
        }
        composable<DestinationScreen.Settings> {
            SettingRoute(onNavigateToHome = {
                navController.navigateUp()
            }, onLogout = {
                navController.navigate(DestinationGraph.Auth) {
                    popUpTo(DestinationGraph.Home) {
                        inclusive = true
                    }
                }
            })
        }
        composable<DestinationScreen.Approved> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Approved")
            }
        }

        composable<DestinationScreen.Capture> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Capture")
            }
        }

        composable<DestinationScreen.Declined> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Declined")
            }
        }
    }
}