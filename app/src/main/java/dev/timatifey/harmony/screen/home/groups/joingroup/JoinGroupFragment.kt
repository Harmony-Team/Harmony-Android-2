package dev.timatifey.harmony.screen.home.groups.joingroup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.timatifey.harmony.common.base.BaseFragment

class JoinGroupFragment : BaseFragment() {

    private lateinit var presenter: JoinGroupPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = presenterFactory.createJoinGroupPresenter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: JoinGroupMvpView = mvpViewFactory.createJoinGroupMvpView(container)
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
            return JoinGroupFragment()
        }
    }
}