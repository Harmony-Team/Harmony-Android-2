package dev.timatifey.harmony.screen.activity

import android.util.Log
import android.view.MenuItem
import dev.timatifey.harmony.R
import dev.timatifey.harmony.common.mvp.MvpPresenter
import dev.timatifey.harmony.common.nav.app.AppScreenNavigator
import dev.timatifey.harmony.common.nav.BackPressDispatcher
import dev.timatifey.harmony.screen.RequireDrawerController
import dev.timatifey.harmony.service.AuthHarmonyService
import kotlinx.coroutines.*

class MainPresenter(
    private val appScreenNavigator: AppScreenNavigator,
    private val authHarmonyService: AuthHarmonyService,
    private val backPressDispatcher: BackPressDispatcher,
) : MvpPresenter<MainMvpView>, MainMvpView.Listener, RequireDrawerController {

    private lateinit var view: MainMvpView
    private lateinit var drawerController: DrawerController

    private val coroutineScope = CoroutineScope(Dispatchers.Main.immediate + SupervisorJob())

    override fun bindView(view: MainMvpView) {
        this.view = view
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu__profile -> appScreenNavigator.toProfile()
            R.id.menu__groups -> appScreenNavigator.toGroupList()
            R.id.menu__settings -> appScreenNavigator.toSettings()
            R.id.menu__about_us -> view.aboutUs()
            R.id.menu__logout -> {
                coroutineScope.launch {
                    authHarmonyService.logoutHarmony()
                }
                drawerController.lockDrawer()
                appScreenNavigator.clearBackStack()
                appScreenNavigator.toAuth()
            }
        }
        view.closeDrawer()
        return true
    }

    override fun onBackPressed(): Boolean {
        Log.e("MainPresenter", "is empty = ${appScreenNavigator.stackIsEmpty}")
        return when {
            view.drawerIsOpen() -> {
                view.closeDrawer()
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

    override fun bindDrawerDispatcher(drawer: DrawerController) {
        this.drawerController = drawer
    }

}