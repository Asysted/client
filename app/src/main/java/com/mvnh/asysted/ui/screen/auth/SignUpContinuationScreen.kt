package com.mvnh.asysted.ui.screen.auth

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.mvnh.asysted.ui.viewmodel.AuthState
import com.mvnh.asysted.ui.viewmodel.AuthViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SignUpContinuationScreen(
    navController: NavController,
    viewModel: AuthViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val authState by viewModel.authState.collectAsState()

    BackHandler {
        (context as Activity).finish()
    }

    if (authState is AuthState.InviteCodeValid) {
        Text(text = "Invite code is valid")
    }
}