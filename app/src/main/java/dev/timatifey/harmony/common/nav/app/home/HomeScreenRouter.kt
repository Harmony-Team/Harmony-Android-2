package dev.timatifey.harmony.common.nav.app.home

import dev.timatifey.harmony.data.model.harmony.HarmonyGroup
import dev.timatifey.harmony.data.model.harmony.HarmonyPlaylist
import dev.timatifey.harmony.data.model.spotify.SpotifyPlaylist

interface HomeScreenRouter {
    fun navigateToProfileFragment()
    fun navigateToSettingsFragment()
    fun navigateToGroupListFragment()
    fun navigateToAddGroupFragment()
    fun navigateToLobbyFragment(group: HarmonyGroup)
    fun navigateToNewGroupFragment()
    fun navigateToShareGroupFragment(group: HarmonyGroup)
    fun navigateToNewPlaylist(playlist: HarmonyPlaylist)
    fun navigateToPlaylistInfo(playlist: SpotifyPlaylist)
}