package dev.timatifey.harmony.common.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import dev.timatifey.harmony.common.app.App
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
    lateinit var appScreenNavigator: AppScreenNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent = (application as App).appComponent
            .newActivityComponentBuilder()
            .activity(this)
            .savedInstanceState(savedInstanceState)
            .build()
        activityComponent.inject(this)
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
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
