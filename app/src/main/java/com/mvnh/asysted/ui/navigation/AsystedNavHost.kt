package com.mvnh.asysted.ui.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mvnh.asysted.ui.screen.auth.RedeemInviteCodeScreen
import com.mvnh.asysted.ui.screen.auth.SignInScreen
import com.mvnh.asysted.ui.screen.auth.SignUpContinuationScreen
import com.mvnh.asysted.ui.screen.auth.WelcomeScreen

@Composable
fun AsystedNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Welcome.route,
        modifier = modifier,
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() }
    ) {
        composable(Screen.Welcome.route) {
            WelcomeScreen(navController)
        }
        composable(Screen.SignIn.route) {
            SignInScreen(navController)
        }
        composable(Screen.RedeemInviteCode.route) {
            RedeemInviteCodeScreen(navController)
        }
        composable(Screen.SignUpContinuation.route) {
            SignUpContinuationScreen(navController)
        }
    }
}