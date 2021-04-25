package dev.timatifey.harmony.screen.home.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.timatifey.harmony.common.base.BaseFragment
import dev.timatifey.harmony.common.mvp.factory.MvpViewFactory
import dev.timatifey.harmony.common.mvp.factory.PresenterFactory
import javax.inject.Inject

class ProfileFragment : BaseFragment() {

    private lateinit var presenter: ProfilePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = presenterFactory.createProfilePresenter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: ProfileMvpView = mvpViewFactory.createProfileMvpView(container)
        presenter.bindView(view)
        presenter.bindDrawerDispatcher(drawerDispatcher)
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
        fun newInstance(): Fragment = ProfileFragment()
    }
}