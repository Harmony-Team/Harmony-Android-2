package dev.timatifey.harmony.screen.auth.signin

import dev.timatifey.harmony.R
import dev.timatifey.harmony.data.Resource
import dev.timatifey.harmony.data.Status
import dev.timatifey.harmony.data.model.harmony.Token
import dev.timatifey.harmony.common.mvp.MvpPresenter
import dev.timatifey.harmony.common.nav.BackPressDispatcher
import dev.timatifey.harmony.common.nav.app.AppScreenNavigator
import dev.timatifey.harmony.screen.activity.MenuController
import dev.timatifey.harmony.screen.RequireMenuController
import dev.timatifey.harmony.screen.activity.MainMvpViewImpl.Companion.POS_PROFILE
import dev.timatifey.harmony.service.AuthHarmonyService
import dev.timatifey.harmony.util.Validator
import kotlinx.coroutines.*

class SignInPresenter(
    private val appScreenNavigator: AppScreenNavigator,
    private val backPressDispatcher: BackPressDispatcher,
    private val authHarmonyService: AuthHarmonyService
) : MvpPresenter<SignInMvpView>, SignInMvpView.Listener, RequireMenuController {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private lateinit var view: SignInMvpView
    private lateinit var menuController: MenuController

    override fun bindView(view: SignInMvpView) {
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

    override fun onBackPressed(): Boolean {
        return false
    }

    override fun onSignInBtnClicked(username: String, password: String) {
        coroutineScope.launch {
            view.showLoading()
            val result: Resource<Token> = authHarmonyService.authHarmony(username, password)
            view.hideLoading()
            when (result.status) {
                is Status.Success -> {
                    appScreenNavigator.toHome()
                    menuController.setSelected(POS_PROFILE)
                    menuController.unlockMenu()
                }
                is Status.Error -> {
                    view.showError(R.string.auth_failed)
                }
            }
        }
    }

    override fun onForgotPasswordTvClicked() {
        appScreenNavigator.toRecovery()
    }

    override fun onHaveNotAccTvClicked() {
        appScreenNavigator.toSignUp()
    }

    override fun onSpotifyAuthBtnClicked() {
        appScreenNavigator.toSpotifyAuthWebView()
    }

    override fun onUsernameFieldTextChanged(text: String) {
        if (Validator.isUserNameValid(text) && Validator.isEmailValid(text)) {
            view.hideUsernameFieldError()
        } else {
            view.showUsernameFieldError(R.string.invalid_username)
        }
    }

    override fun onPasswordFieldTextChanged(text: String) {
        if (!Validator.isPasswordValid(text)) {
            view.showPasswordFieldError(R.string.invalid_password)
        } else {
            view.hidePasswordFieldError()
        }
    }

    override fun bindMenuController(menuController: MenuController) {
        this.menuController = menuController
    }

}