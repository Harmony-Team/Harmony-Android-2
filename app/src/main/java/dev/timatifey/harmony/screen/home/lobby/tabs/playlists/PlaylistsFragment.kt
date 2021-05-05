package dev.timatifey.harmony.screen.home.lobby.tabs.playlists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.timatifey.harmony.R
import dev.timatifey.harmony.common.base.BaseFragment
import dev.timatifey.harmony.screen.home.lobby.tabs.LobbyTabsFragment
import dev.timatifey.harmony.screen.home.lobby.tabs.music.MusicMvpView
import dev.timatifey.harmony.screen.home.lobby.tabs.music.MusicPresenter

class PlaylistsFragment : BaseFragment() {

    private lateinit var presenter: PlaylistsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = presenterFactory.createPlaylistsPresenter(
            (requireParentFragment() as LobbyTabsFragment).lobbyFragmentNavigator
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: PlaylistsMvpView = mvpViewFactory.createPlaylistsMvpView(container)
        presenter.bindView(view)
        return view.rootView
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    companion object {
        fun newInstance(): Fragment {
            return PlaylistsFragment()
        }
    }
}