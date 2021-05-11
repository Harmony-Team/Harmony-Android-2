package dev.timatifey.harmony.screen.activity

import android.util.Log
import dev.timatifey.harmony.common.mvp.MvpPresenter
import dev.timatifey.harmony.common.nav.app.AppScreenNavigator
import dev.timatifey.harmony.common.nav.BackPressDispatcher
import dev.timatifey.harmony.screen.RequireMenuController
import dev.timatifey.harmony.screen.activity.MainMvpViewImpl.Companion.POS_GROUPS
import dev.timatifey.harmony.screen.activity.MainMvpViewImpl.Companion.POS_LOGOUT
import dev.timatifey.harmony.screen.activity.MainMvpViewImpl.Companion.POS_PROFILE
import dev.timatifey.harmony.screen.activity.MainMvpViewImpl.Companion.POS_SETTINGS
import dev.timatifey.harmony.service.AuthHarmonyService
import kotlinx.coroutines.*
import java.lang.IllegalArgumentException

class MainPresenter(
    private val appScreenNavigator: AppScreenNavigator,
    private val authHarmonyService: AuthHarmonyService,
    private val backPressDispatcher: BackPressDispatcher,
) : MvpPresenter<MainMvpView>, MainMvpView.Listener, RequireMenuController {

    private lateinit var view: MainMvpView
    private lateinit var menuController: MenuController

    private val coroutineScope = CoroutineScope(Dispatchers.Main.immediate + SupervisorJob())

    override fun bindView(view: MainMvpView) {
        this.view = view
    }

    override fun onItemSelected(position: Int) {
        Log.e("MainPresenter", "pos=$position, POS_LOG=$POS_LOGOUT")
        when (position) {
            POS_PROFILE -> appScreenNavigator.toProfile()
            POS_GROUPS -> appScreenNavigator.toGroupList()
            POS_SETTINGS -> appScreenNavigator.toSettings()
            POS_LOGOUT -> {
                coroutineScope.launch {
                    authHarmonyService.logoutHarmony()
                }
                menuController.lockMenu()
                appScreenNavigator.clearBackStack()
                appScreenNavigator.toAuth()
            }
            else -> throw IllegalArgumentException("Position $position is not define")
        }
        view.closeMenu()
    }

    override fun onBackPressed(): Boolean {
        Log.e("MainPresenter", "is empty = ${appScreenNavigator.stackIsEmpty}")
        return when {
            view.drawerIsOpen() -> {
                view.closeMenu()
                true
            }
            else -> false
        }
    }

    override fun onStart() {
        view.registerListener(this)
        backPressDispatcher.registerListener(this)
    }

    override fun onStop() {
        view.unregisterListener(this)
        backPressDispatcher.unregisterListener(this)
    }

    override fun onDestroy() {
        coroutineScope.coroutineContext.cancelChildren()
    }

    override fun bindMenuController(menuController: MenuController) {
        this.menuController = menuController
    }

}