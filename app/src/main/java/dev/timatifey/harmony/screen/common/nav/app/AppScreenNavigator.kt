package dev.timatifey.harmony.screen.common.nav.app

import dev.timatifey.harmony.data.model.harmony.User

interface AppScreenNavigator {
    fun toSignInFragment()
    fun toSignUpFragment()
    fun toRecoveryFragment()
    fun toSpotifyAuthWebView()
    fun toHomeScreen(user: User)
}