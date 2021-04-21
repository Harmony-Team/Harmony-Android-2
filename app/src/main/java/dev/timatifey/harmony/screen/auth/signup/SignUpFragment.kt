package dev.timatifey.harmony.screen.auth.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.timatifey.harmony.common.base.BaseFragment
import dev.timatifey.harmony.common.mvp.factory.MvpViewFactory
import dev.timatifey.harmony.common.mvp.factory.PresenterFactory
import javax.inject.Inject

class SignUpFragment: BaseFragment() {

    private lateinit var presenter: SignUpPresenter

    @Inject
    lateinit var mvpViewFactory: MvpViewFactory
    @Inject
    lateinit var presenterFactory: PresenterFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        presentationComponent.inject(this)
        super.onCreate(savedInstanceState)
        presenter = presenterFactory.createSignUpPresenter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: SignUpMvpView = mvpViewFactory.createSignUpMvpView(container)
        presenter.bindView(view)
        presenter.bindDrawerDispatcher(drawerDispatcher)
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
        fun newInstance(): Fragment = SignUpFragment()
    }
}