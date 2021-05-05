package dev.timatifey.harmony.screen.home.lobby.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.timatifey.harmony.common.base.BaseFragment
import dev.timatifey.harmony.common.nav.lobby.LobbyFragmentNavigator
import dev.timatifey.harmony.screen.home.lobby.LobbyFragment


class LobbyTabsFragment : BaseFragment() {

    private lateinit var presenter: LobbyTabsPresenter
    lateinit var pageController: LobbyTabsPageController
    lateinit var lobbyFragmentNavigator: LobbyFragmentNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lobbyFragmentNavigator = (requireParentFragment() as LobbyFragment).lobbyFragmentNavigator
        presenter = presenterFactory.createLobbyTabsPresenter(
            lobbyFragmentNavigator = lobbyFragmentNavigator,
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewPagerAdapter = ViewPagerAdapter(this)
        val view = mvpViewFactory.createLobbyTabsMvpView(container, viewPagerAdapter)
        pageController = view
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
            return LobbyTabsFragment()
        }
    }
}