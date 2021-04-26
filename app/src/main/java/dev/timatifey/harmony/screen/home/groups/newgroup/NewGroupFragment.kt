package dev.timatifey.harmony.screen.home.groups.newgroup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.timatifey.harmony.common.base.BaseFragment

class NewGroupFragment: BaseFragment() {
    private lateinit var presenter: NewGroupPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        presenter = presenterFactory.createNewGroupPresenter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: NewGroupMvpView = mvpViewFactory.createNewGroupMvpView(container)
//        presenter.bindView(view)
        return view.rootView
    }

//    override fun onStart() {
//        presenter.onStart()
//        super.onStart()
//    }
//
//    override fun onStop() {
//        presenter.onStop()
//        super.onStop()
//    }
//
//    override fun onDestroy() {
//        presenter.onDestroy()
//        super.onDestroy()
//    }

    companion object {
        fun newInstance(): Fragment {
            return NewGroupFragment()
        }
    }
}