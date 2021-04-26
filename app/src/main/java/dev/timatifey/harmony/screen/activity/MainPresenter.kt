package dev.timatifey.harmony.screen.activity

import android.util.Log
import android.view.MenuItem
import dev.timatifey.harmony.R
import dev.timatifey.harmony.common.mvp.MvpPresenter
import dev.timatifey.harmony.common.nav.AppScreenNavigator
import dev.timatifey.harmony.common.nav.BackPressDispatcher
import dev.timatifey.harmony.screen.RequireDrawerDispatcher
import dev.timatifey.harmony.service.AuthHarmonyService
import dev.timatifey.harmony.service.UserService
import kotlinx.coroutines.*

class MainPresenter(
    private val appScreenNavigator: AppScreenNavigator,
    private val authHarmonyService: AuthHarmonyService,
    private val userService: UserService,
    private val backPressDispatcher: BackPressDispatcher,
) : MvpPresenter<MainMvpView>, MainMvpView.Listener, RequireDrawerDispatcher {

    private lateinit var view: MainMvpView
    private lateinit var drawerDispatcher: DrawerDispatcher

    private val coroutineScope = CoroutineScope(Dispatchers.Main.immediate + SupervisorJob())
    override fun bindView(view: MainMvpView) {
        this.view = view
        loadUsername()
    }

    private fun loadUsername() {
        coroutineScope.launch {
            val user = userService.getUser().data
            if (user != null) {
                view.setUsername(user.username)
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu__profile -> appScreenNavigator.toProfile()
            R.id.menu__groups -> appScreenNavigator.toGroupList()
            R.id.menu__settings -> appScreenNavigator.toSettings()
            R.id.menu__about_us -> view.aboutUs()
            R.id.menu__logout -> {
                authHarmonyService.logoutHarmony()
                drawerDispatcher.lockDrawer()
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
    }

    override fun bindDrawerDispatcher(drawer: DrawerDispatcher) {
        this.drawerDispatcher = drawer
    }

}