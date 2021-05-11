package dev.timatifey.harmony.screen.home.lobby

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import dev.timatifey.harmony.app.Config.Companion.SHARING_REQUEST_CODE
import dev.timatifey.harmony.common.base.BaseFragment
import dev.timatifey.harmony.common.nav.lobby.LobbyFragmentNavigator
import dev.timatifey.harmony.repo.lobby.LobbyProvider
import dev.timatifey.harmony.screen.home.group.share.ShareIntentListener
import dev.timatifey.harmony.util.createShareLinkIntent
import javax.inject.Inject

class LobbyFragment : BaseFragment(), ShareIntentListener {

    private lateinit var presenter: LobbyPresenter
    lateinit var lobbyFragmentNavigator: LobbyFragmentNavigator

    @Inject
    lateinit var lobbyProvider: LobbyProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)
        val groupId = arguments!!.getLong(ARG_GROUP)
        lobbyProvider.setGroupId(groupId)
        lobbyFragmentNavigator =
            LobbyFragmentNavigator(lobbyProvider, childFragmentManager, savedInstanceState)
        presenter = presenterFactory.createLobbyPresenter(listenerShare = this)
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
        startActivityForResult(
            Intent.createChooser(
                createShareLinkIntent(code),
                "Choose messenger"
            ), SHARING_REQUEST_CODE
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            SHARING_REQUEST_CODE -> presenter.onShareResult(resultCode, data)
        }
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