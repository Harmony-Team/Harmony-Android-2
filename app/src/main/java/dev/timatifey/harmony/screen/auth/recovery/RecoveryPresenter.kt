package dev.timatifey.harmony.screen.auth.recovery

import dev.timatifey.harmony.R
import dev.timatifey.harmony.common.mvp.MvpPresenter
import dev.timatifey.harmony.common.nav.BackPressDispatcher
import dev.timatifey.harmony.common.nav.AppScreenNavigator
import dev.timatifey.harmony.data.Status
import dev.timatifey.harmony.screen.activity.DrawerDispatcher
import dev.timatifey.harmony.screen.RequireDrawerDispatcher
import dev.timatifey.harmony.service.AuthService
import dev.timatifey.harmony.util.Validator
import kotlinx.coroutines.*

class RecoveryPresenter(
    private val appScreenNavigator: AppScreenNavigator,
    private val backPressDispatcher: BackPressDispatcher,
    private val authService: AuthService
) : MvpPresenter<RecoveryMvpView>, RecoveryMvpView.Listener, RequireDrawerDispatcher {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private lateinit var view: RecoveryMvpView
    private lateinit var drawerDispatcher: DrawerDispatcher

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
            val result = authService.recoveryAcc(email)
            view.hideLoading()
            when (result.status) {
                is Status.Success -> {
                    appScreenNavigator.toHome()
                    drawerDispatcher.unlockDrawer()
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

    override fun bindDrawerDispatcher(drawerDispatcher: DrawerDispatcher) {
        this.drawerDispatcher = drawerDispatcher
    }
}