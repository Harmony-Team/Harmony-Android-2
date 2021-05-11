package dev.timatifey.harmony.common.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import dev.timatifey.harmony.common.mvp.factory.MvpViewFactory
import dev.timatifey.harmony.common.mvp.factory.PresenterFactory
import dev.timatifey.harmony.common.nav.app.AppScreenNavigator
import dev.timatifey.harmony.di.component.ActivityComponent
import dev.timatifey.harmony.screen.activity.MenuController
import dev.timatifey.harmony.screen.activity.MainActivity
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    lateinit var menuController: MenuController

    protected val activityComponent: ActivityComponent
        get() = (requireActivity() as BaseActivity).activityComponent

    protected val appScreenNavigator: AppScreenNavigator
        get() = (requireActivity() as BaseActivity).appScreenNavigator

    @Inject
    lateinit var mvpViewFactory: MvpViewFactory

    @Inject
    lateinit var presenterFactory: PresenterFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)
        menuController = (activity as MainActivity).menuController
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        appScreenNavigator.onSaveInstanceState(outState)
    }
}
