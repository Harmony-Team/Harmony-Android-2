package dev.timatifey.harmony.screen.home.profile

import dev.timatifey.harmony.R
import dev.timatifey.harmony.common.mvp.MvpPresenter
import dev.timatifey.harmony.common.nav.BackPressDispatcher
import dev.timatifey.harmony.screen.RequireMenuController
import dev.timatifey.harmony.screen.activity.MenuController
import dev.timatifey.harmony.service.UserService
import kotlinx.coroutines.*

class ProfilePresenter(
    private val userService: UserService,
    private val backPressDispatcher: BackPressDispatcher,
): MvpPresenter<ProfileMvpView>, ProfileMvpView.Listener, RequireMenuController {

    private lateinit var view: ProfileMvpView
    private lateinit var menu: MenuController

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    override fun bindView(view: ProfileMvpView) {
        this.view = view
        initUser()
    }

    private fun initUser() {
        coroutineScope.launch {
            val user = userService.getUser().data
            if (user != null) {
                view.setUsername(user.username)
                menu.setMenuUsername(user.username)
            } else {
                view.showMessage(R.string.loading_failed)
            }
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

    override fun onMenuBtnClicked() {
        menu.openMenu()
    }

    override fun onBackPressed(): Boolean {
        return false
    }

    override fun bindMenuController(menuController: MenuController) {
        this.menu = menuController
    }
}