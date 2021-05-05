package dev.timatifey.harmony.common.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.timatifey.harmony.app.App
import dev.timatifey.harmony.common.mvp.factory.MvpViewFactory
import dev.timatifey.harmony.common.mvp.factory.PresenterFactory
import dev.timatifey.harmony.di.component.ActivityComponent
import dev.timatifey.harmony.common.nav.BackPressDispatcher
import dev.timatifey.harmony.common.nav.BackPressedListener
import dev.timatifey.harmony.common.nav.app.AppScreenNavigator
import javax.inject.Inject


abstract class BaseActivity : AppCompatActivity(), BackPressDispatcher {

    lateinit var activityComponent: ActivityComponent
        private set

    private val backPressedListeners: MutableSet<BackPressedListener> = HashSet()

    @Inject
    lateinit var presenterFactory: PresenterFactory
    @Inject
    lateinit var mvpViewFactory: MvpViewFactory
    @Inject
    lateinit var appScreenNavigator: AppScreenNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        activityComponent = (application as App).appComponent
            .newActivityComponentBuilder()
            .activity(this)
            .savedInstanceState(savedInstanceState)
            .build()
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        appScreenNavigator.onSaveInstanceState(outState)
    }

    override fun registerListener(listener: BackPressedListener) {
        backPressedListeners.add(listener);
    }

    override fun unregisterListener(listener: BackPressedListener) {
        backPressedListeners.remove(listener)
    }

    override fun onBackPressed() {
        var isBackPressConsumedByAnyListener = false
        backPressedListeners.forEach { listener ->
            if (listener.onBackPressed()) {
                isBackPressConsumedByAnyListener = true
            }
        }
        if (!isBackPressConsumedByAnyListener) {
            super.onBackPressed()
        }
    }
}
