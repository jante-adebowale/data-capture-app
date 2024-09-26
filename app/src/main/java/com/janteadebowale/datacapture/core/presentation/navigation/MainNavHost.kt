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
    graph: Graph,
) {
    NavHost(navController = navController, startDestination = graph) {
        authGraph(navController)
        homeGraph(navController)
    }
}

fun NavGraphBuilder.authGraph(navController: NavHostController) {
    navigation<Graph.Auth>(startDestination = Screens.Login) {
        composable<Screens.Login> {
            LoginRoute(onNavigateToSignup = {
                navController.navigate(Screens.Signup)
            }, onNavigateToHome = {
                navController.navigate(Graph.Home) {
                    popUpTo(Graph.Auth) {
                        inclusive = true
                    }
                }
            })
        }

        composable<Screens.Signup> {
            SignupRoute(onNavigateToLogin = {
                navController.navigate(Screens.Login)
            })
        }

    }
}

fun NavGraphBuilder.homeGraph(navController: NavHostController) {
    navigation<Graph.Home>(startDestination = Screens.Capture) {
        composable<Screens.Capture> {
            HomeRoute(onNavigateToSettings = {
                navController.navigate(Screens.Settings)
            })
        }
        composable<Screens.Settings> {
            SettingRoute(onNavigateToHome = {
                navController.navigateUp()
            }, onLogout = {
                navController.navigate(Graph.Auth){
                    popUpTo(Graph.Home){
                        inclusive = true
                    }
                }
            })
        }
        composable<Screens.Approved> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Approved")
            }
        }
        composable<Screens.Declined> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Declined")
            }
        }
    }
}