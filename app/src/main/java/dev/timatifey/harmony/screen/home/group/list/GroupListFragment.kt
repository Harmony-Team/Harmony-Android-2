package dev.timatifey.harmony.screen.home.group.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.timatifey.harmony.common.base.BaseFragment

class GroupListFragment : BaseFragment() {

    private lateinit var presenter: GroupListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = presenterFactory.createGroupListPresenter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: GroupListMvpView = mvpViewFactory.createGroupListMvpView(container)
        presenter.bindDrawerDispatcher(drawerController)
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
        fun newInstance(): Fragment {
            return GroupListFragment()
        }
    }
}