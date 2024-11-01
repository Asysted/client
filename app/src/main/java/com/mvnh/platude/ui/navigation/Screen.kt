package com.mvnh.platude.ui.navigation

sealed class Screen(val route: String) {
    data object Auth : Screen("auth")
}