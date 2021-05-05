package dev.timatifey.harmony.screen.home.lobby

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.timatifey.harmony.common.base.BaseFragment
import dev.timatifey.harmony.common.nav.lobby.LobbyFragmentNavigator
import dev.timatifey.harmony.screen.home.group.share.ShareIntentListener
import dev.timatifey.harmony.util.createShareLinkIntent

class LobbyFragment : BaseFragment(), ShareIntentListener {

    private lateinit var presenter: LobbyPresenter
    lateinit var lobbyFragmentNavigator: LobbyFragmentNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val groupId = arguments!!.getLong(ARG_GROUP)

        val lobbyStateMachine = presenterFactory.createLobbyStateMachine(groupId)
        lobbyFragmentNavigator =
            LobbyFragmentNavigator(
                groupId,
                lobbyStateMachine,
                childFragmentManager,
                savedInstanceState
            )

        presenter = presenterFactory.createLobbyPresenter(groupId = groupId, listenerShare = this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: LobbyMvpView = mvpViewFactory.createLobbyMvpView(container)
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

    override fun startActivityForShare(code: String) {
        startActivity(Intent.createChooser(createShareLinkIntent(code), "Choose messenger"))
    }


    companion object {
        const val ARG_GROUP = "ARG_GROUP"

        fun newInstance(groupId: Long): Fragment {
            val fragment: Fragment = LobbyFragment()
            val args: Bundle = Bundle()
            args.putLong(ARG_GROUP, groupId)
            fragment.arguments = args
            return fragment
        }
    }
}