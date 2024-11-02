package com.mvnh.platude.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mvnh.platude.R
import com.mvnh.platude.ui.navigation.BottomNavItem
import com.mvnh.platude.ui.navigation.PlatudeNavHost
import com.mvnh.platude.ui.navigation.Screen

@Composable
fun MainScreen() {
    val topLevelDestinations = listOf(
        BottomNavItem(
            route = Screen.Auth.route,
            labelRes = R.string.auth,
            selectedIcon = Icons.Filled.Lock,
            unselectedIcon = Icons.Default.Lock
        )
    )

    val localNavController = rememberNavController()
    val currentRoute = localNavController.currentBackStackEntryAsState().value?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute in topLevelDestinations.map { it.route }) {
//                PlatudeNavBar(
//                    navController = localNavController,
//                    destinations = topLevelDestinations
//                )
            }
        },
        contentWindowInsets = WindowInsets(top = 0.dp)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .safeDrawingPadding()
        ) {
            PlatudeNavHost(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                navController = localNavController
            )
        }
    }
}