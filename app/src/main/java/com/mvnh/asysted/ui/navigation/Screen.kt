package com.mvnh.asysted.ui.navigation

sealed class Screen(val route: String) {
    data object Welcome : Screen("welcome")
    data object SignIn : Screen("sign_in")
    data object RedeemInviteCode : Screen("redeem_invite_code")
    data object SignUpContinuation : Screen("sign_up_continuation")
}