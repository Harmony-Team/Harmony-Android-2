package dev.timatifey.harmony.common.base

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import dev.timatifey.harmony.common.mvp.factory.MvpViewFactory
import dev.timatifey.harmony.common.mvp.factory.PresenterFactory
import dev.timatifey.harmony.common.nav.AppScreenNavigator
import dev.timatifey.harmony.di.component.ActivityComponent
import dev.timatifey.harmony.screen.activity.DrawerDispatcher
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    lateinit var drawerDispatcher: DrawerDispatcher

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
    }

    override fun onAttach(context: Context) {
        drawerDispatcher = activity as DrawerDispatcher
        super.onAttach(context)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        appScreenNavigator.onSaveInstanceState(outState)
    }
}
