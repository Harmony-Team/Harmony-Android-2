package dev.timatifey.harmony.common.nav

interface AppScreenRouter {
    fun toSignIn()
    fun toSignUp()
    fun toRecovery()
    fun toSpotifyAuthWebView()
    fun toProfile()
    fun toSettings()
    fun toGroupList()

    fun toHome()
    fun toAuth()
}