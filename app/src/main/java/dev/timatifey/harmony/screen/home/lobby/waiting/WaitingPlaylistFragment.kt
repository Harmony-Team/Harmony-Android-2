package dev.timatifey.harmony.screen.home.lobby.waiting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.timatifey.harmony.common.base.BaseFragment
import dev.timatifey.harmony.screen.home.lobby.LobbyFragment

class WaitingPlaylistFragment : BaseFragment() {

    private lateinit var presenter: WaitingPlaylistPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = presenterFactory.createWaitingPlaylistPresenter(
            lobbyFragmentNavigator = (requireParentFragment() as LobbyFragment).lobbyFragmentNavigator,
        )

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: WaitingPlaylistMvpView = mvpViewFactory.createWaitingPlaylistMvpView(container)
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
            return WaitingPlaylistFragment()
        }
    }
}