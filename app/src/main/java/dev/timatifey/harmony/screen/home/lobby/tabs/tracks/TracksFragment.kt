package dev.timatifey.harmony.screen.home.lobby.tabs.tracks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.timatifey.harmony.common.base.BaseFragment
import dev.timatifey.harmony.screen.home.lobby.tabs.LobbyTabsFragment

class TracksFragment : BaseFragment() {

    private lateinit var presenter: TracksPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val lobbyTabsFragment = (requireParentFragment() as LobbyTabsFragment)
        presenter = presenterFactory.createTracksPresenter(
            pageController = lobbyTabsFragment.pageController,
            lobbyFragmentNavigator = lobbyTabsFragment.lobbyFragmentNavigator
        )

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: TracksMvpView = mvpViewFactory.createTracksMvpView(container)
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
            return TracksFragment()
        }
    }
}