package com.mvnh.platude.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mvnh.platude.R
import com.mvnh.platude.ui.viewmodel.AuthState
import com.mvnh.platude.ui.viewmodel.AuthViewModel
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreen(viewModel: AuthViewModel = koinViewModel()) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    var inviteCode by rememberSaveable { mutableStateOf("") }
    val authState by viewModel.authState.collectAsState()
    val isErrorMessageVisible = authState is AuthState.Error
    val isLoading = authState is AuthState.Loading

    LaunchedEffect(authState) {
        if (authState is AuthState.Success<*>) {
            Log.d("Platude", (authState as AuthState.Success<*>).data.toString())

            snackbarHostState.showSnackbar(context.resources.getString(R.string.redeem_success))
        } else if (authState is AuthState.Error) {
            Log.e("Platude", (authState as AuthState.Error).message)

            snackbarHostState.showSnackbar((authState as AuthState.Error).message)
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.auth)
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = inviteCode,
                onValueChange = { if (it.length <= 36) inviteCode = it },
                modifier = Modifier.width(300.dp),
                enabled = !isLoading,
                label = {
                    Text(
                        text = stringResource(id = R.string.invite_code)
                    )
                },
                trailingIcon = {
                    IconButton(onClick = {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(context.resources.getString(R.string.invite_code_info))
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = stringResource(id = R.string.invite_code_info)
                        )
                    }
                },
                supportingText = {
                    if (isErrorMessageVisible) {
                        Text(
                            text = (authState as AuthState.Error).message
                        )
                    }
                },
                isError = isErrorMessageVisible,
                singleLine = true
            )

            Button(
                onClick = {
                    coroutineScope.launch {
                        viewModel.redeemInviteCode(inviteCode)
                    }
                },
                enabled = !isLoading
            ) {
                Text(
                    text = stringResource(id = R.string.redeem)
                )
            }
        }
    }
}