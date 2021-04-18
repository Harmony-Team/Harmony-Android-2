package dev.timatifey.harmony.screen.common.nav

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ncapdevi.fragnav.FragNavController
import dev.timatifey.harmony.data.model.harmony.HarmonyGroup
import dev.timatifey.harmony.data.model.harmony.HarmonyPlaylist
import dev.timatifey.harmony.data.model.harmony.User
import dev.timatifey.harmony.data.model.spotify.SpotifyPlaylist

class ScreenNavigator(
    private val fragNavController: FragNavController,
    savedInstanceState: Bundle?
) : FragNavController.RootFragmentListener, AppScreenRouter {

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

    override fun toSignInFragment() {
        TODO("Not yet implemented")
    }

    override fun toSignUpFragment() {
        TODO("Not yet implemented")
    }

    override fun toRecoveryFragment() {
        TODO("Not yet implemented")
    }

    override fun toHomeScreen(user: User) {
        TODO("Not yet implemented")
    }

    override fun toProfileFragment() {
        TODO("Not yet implemented")
    }

    override fun toSettingsFragment() {
        TODO("Not yet implemented")
    }

    override fun toSpotifyAuthWebView() {
        TODO("Not yet implemented")
    }

    override fun toGroupListFragment() {
        TODO("Not yet implemented")
    }

    override fun toAddGroupFragment() {
        TODO("Not yet implemented")
    }

    override fun toLobbyFragment(group: HarmonyGroup) {
        TODO("Not yet implemented")
    }

    override fun toNewGroupFragment() {
        TODO("Not yet implemented")
    }

    override fun toShareGroupFragment(group: HarmonyGroup) {
        TODO("Not yet implemented")
    }

    override fun toNewPlaylist(playlist: HarmonyPlaylist) {
        TODO("Not yet implemented")
    }

    override fun toPlaylistInfo(playlist: SpotifyPlaylist) {
        TODO("Not yet implemented")
    }

}
