package dev.timatifey.harmony.screen.common.nav.app.home.lobby

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ncapdevi.fragnav.FragNavController
import dev.timatifey.harmony.data.model.harmony.HarmonyGroup
import dev.timatifey.harmony.data.model.harmony.HarmonyPlaylist
import dev.timatifey.harmony.data.model.spotify.SpotifyPlaylist

class LobbyFragmentNavigatorImpl(
    private val fragNavController: FragNavController,
    savedInstanceState: Bundle?
) : FragNavController.RootFragmentListener, LobbyFragmentNavigator {

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

    override fun navigateToMusic() {
        TODO("Not yet implemented")
    }

    override fun navigateToPlaylists() {
        TODO("Not yet implemented")
    }

    override fun navigateToWaitingPlaylist() {
        TODO("Not yet implemented")
    }

}
