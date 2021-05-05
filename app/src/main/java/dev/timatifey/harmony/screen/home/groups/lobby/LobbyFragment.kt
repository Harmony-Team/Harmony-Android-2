package dev.timatifey.harmony.screen.home.groups.lobby

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.timatifey.harmony.common.base.BaseFragment
import dev.timatifey.harmony.screen.home.groups.grouplist.GroupListMvpView
import dev.timatifey.harmony.screen.home.groups.grouplist.GroupListPresenter
import dev.timatifey.harmony.screen.home.groups.sharegroup.ShareGroupFragment

class LobbyFragment: BaseFragment() {

    private lateinit var presenter: LobbyPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val groupId = arguments!!.getLong(ARG_GROUP)
        presenter = presenterFactory.createLobbyPresenter(groupId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: LobbyMvpView = mvpViewFactory.createLobbyMvpView(container)
        presenter.bindDrawerDispatcher(drawerDispatcher)
        presenter.bindView(view)
        return view.rootView
    }

    override fun onStart() {
        presenter.onStart()
        super.onStart()
    }

    override fun onStop() {
        presenter.onStop()
        super.onStop()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
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