package dev.timatifey.harmony.screen.common.nav.app.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ncapdevi.fragnav.FragNavController
import dev.timatifey.harmony.data.model.harmony.HarmonyGroup
import dev.timatifey.harmony.data.model.harmony.HarmonyPlaylist
import dev.timatifey.harmony.data.model.spotify.SpotifyPlaylist

class HomeScreenNavigatorImpl(
    private val fragNavController: FragNavController,
    savedInstanceState: Bundle?
) : FragNavController.RootFragmentListener, HomeScreenNavigator {

    init {
        fragNavController.rootFragmentListener = this
        fragNavController.initialize(FragNavController.TAB1, savedInstanceState);
    }


    override val numberOfRootFragments: Int
        get() = 1

    override fun getRootFragment(index: Int): Fragment {
        TODO("Not yet implemented")
    }


    fun onSaveInstanceState(outState: Bundle?) {
        fragNavController.onSaveInstanceState(outState)
    }

    fun navigateUp() {
        fragNavController.popFragment()
    }

    override fun navigateToProfileFragment() {
        TODO("Not yet implemented")
    }

    override fun navigateToSettingsFragment() {
        TODO("Not yet implemented")
    }

    override fun navigateToGroupListFragment() {
        TODO("Not yet implemented")
    }

    override fun navigateToAddGroupFragment() {
        TODO("Not yet implemented")
    }

    override fun navigateToLobbyFragment(group: HarmonyGroup) {
        TODO("Not yet implemented")
    }

    override fun navigateToNewGroupFragment() {
        TODO("Not yet implemented")
    }

    override fun navigateToShareGroupFragment(group: HarmonyGroup) {
        TODO("Not yet implemented")
    }

    override fun navigateToNewPlaylist(playlist: HarmonyPlaylist) {
        TODO("Not yet implemented")
    }

    override fun navigateToPlaylistInfo(playlist: SpotifyPlaylist) {
        TODO("Not yet implemented")
    }

}
