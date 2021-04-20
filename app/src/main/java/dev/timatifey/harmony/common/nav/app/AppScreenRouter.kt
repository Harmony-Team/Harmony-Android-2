package dev.timatifey.harmony.common.nav.app

import dev.timatifey.harmony.data.model.harmony.Token

interface AppScreenRouter {
    fun toSignIn()
    fun toSignUp()
    fun toRecovery()
    fun toSpotifyAuthWebView()
    fun toHome(token: Token)
}