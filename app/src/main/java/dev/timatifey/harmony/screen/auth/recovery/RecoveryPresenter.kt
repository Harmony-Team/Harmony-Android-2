package dev.timatifey.harmony.screen.auth.recovery

import dev.timatifey.harmony.R
import dev.timatifey.harmony.common.mvp.MvpPresenter
import dev.timatifey.harmony.common.nav.BackPressDispatcher
import dev.timatifey.harmony.common.nav.app.AppScreenNavigator
import dev.timatifey.harmony.data.Status
import dev.timatifey.harmony.screen.activity.MenuController
import dev.timatifey.harmony.screen.RequireMenuController
import dev.timatifey.harmony.service.AuthHarmonyService
import dev.timatifey.harmony.util.Validator
import kotlinx.coroutines.*

class RecoveryPresenter(
    private val appScreenNavigator: AppScreenNavigator,
    private val backPressDispatcher: BackPressDispatcher,
    private val authHarmonyService: AuthHarmonyService
) : MvpPresenter<RecoveryMvpView>, RecoveryMvpView.Listener, RequireMenuController {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private lateinit var view: RecoveryMvpView
    private lateinit var menuController: MenuController

    override fun bindView(view: RecoveryMvpView) {
        this.view = view
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

    override fun onRecoveryBtnClicked(email: String) {
        coroutineScope.launch {
            view.showLoading()
            val result = authHarmonyService.recoveryAcc(email)
            view.hideLoading()
            when (result.status) {
                is Status.Success -> {
                    appScreenNavigator.toHome()
                    menuController.unlockMenu()
                }
                is Status.Error -> {
                    view.showError(R.string.auth_failed)
                }
            }
        }
    }

    override fun onHaveNotAccTvClicked() {
        appScreenNavigator.toSignUp()
    }

    override fun onEmailFieldTextChanged(text: String) {
        if (!Validator.isEmailValid(text)) {
            view.showEmailFieldError(R.string.invalid_username)
        } else {
            view.hideEmailFieldError()
        }
    }

    override fun onBackPressed(): Boolean {
        appScreenNavigator.navigateUp()
        return true
    }

    override fun bindMenuController(menuController: MenuController) {
        this.menuController = menuController
    }
}