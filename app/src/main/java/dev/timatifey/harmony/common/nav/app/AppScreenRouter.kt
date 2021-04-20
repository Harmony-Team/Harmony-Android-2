package dev.timatifey.harmony.common.nav.app

import dev.timatifey.harmony.data.model.harmony.Token

interface AppScreenRouter {
    fun fromSplashToAuth()
    fun fromSplashToHome()
    fun navigateToSignInFragment()
    fun navigateToSignUpFragment()
    fun navigateToRecoveryFragment()
    fun navigateToSpotifyAuthWebView()
    fun navigateToHomeScreen(token: Token)
}