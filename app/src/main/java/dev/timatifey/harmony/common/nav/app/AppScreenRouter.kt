package dev.timatifey.harmony.common.nav.app

interface AppScreenRouter {
    fun toSignIn()
    fun toSignUp()
    fun toRecovery()
    fun toSpotifyAuthWebView()
    fun toProfile()
    fun toSettings()
    fun toGroupList()
    fun toAddGroupFragment()
    fun toLobby(groupId: Long)

    fun toJoinGroup()
    fun toNewGroup()
    fun toShareGroup(link: String, groupId: Long)

    fun toHome()
    fun toAuth()
}