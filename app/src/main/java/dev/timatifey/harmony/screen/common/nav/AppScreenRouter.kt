package dev.timatifey.harmony.screen.common.nav

import dev.timatifey.harmony.data.model.harmony.HarmonyGroup
import dev.timatifey.harmony.data.model.harmony.HarmonyPlaylist
import dev.timatifey.harmony.data.model.harmony.User
import dev.timatifey.harmony.data.model.spotify.SpotifyPlaylist

interface AppScreenRouter {
    fun toSignInFragment()
    fun toSignUpFragment()
    fun toRecoveryFragment()

    fun toHomeScreen(user: User)
    fun toProfileFragment()
    fun toSettingsFragment()
    fun toSpotifyAuthWebView()

    fun toGroupListFragment()
    fun toAddGroupFragment()
    fun toLobbyFragment(group: HarmonyGroup)
    fun toNewGroupFragment()
    fun toShareGroupFragment(group: HarmonyGroup)
    fun toNewPlaylist(playlist: HarmonyPlaylist)
    fun toPlaylistInfo(playlist: SpotifyPlaylist)
}