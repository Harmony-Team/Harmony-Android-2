package dev.timatifey.harmony.screen.auth.recovery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.timatifey.harmony.common.base.BaseFragment

class RecoveryFragment: BaseFragment() {

    private lateinit var presenter: RecoveryPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = presenterFactory.createRecoveryPresenter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: RecoveryMvpView = mvpViewFactory.createRecoveryMvpView(container)
        presenter.bindView(view)
        presenter.bindDrawerDispatcher(drawerController)
        return view.rootView
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
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
        fun newInstance(): Fragment = RecoveryFragment()
    }
}