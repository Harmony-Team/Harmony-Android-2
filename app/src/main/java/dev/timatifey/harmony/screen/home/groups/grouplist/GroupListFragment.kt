package dev.timatifey.harmony.screen.home.groups.grouplist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.timatifey.harmony.common.base.BaseFragment
import dev.timatifey.harmony.common.mvp.factory.MvpViewFactory
import javax.inject.Inject

class GroupListFragment: BaseFragment() {

    @Inject
    lateinit var mvpViewFactory: MvpViewFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        presentationComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: GroupListMvpView = mvpViewFactory.createGroupListMvpView(container)
        return view.rootView
    }

    companion object {
        fun newInstance(): Fragment {
            return GroupListFragment()
        }
    }
}