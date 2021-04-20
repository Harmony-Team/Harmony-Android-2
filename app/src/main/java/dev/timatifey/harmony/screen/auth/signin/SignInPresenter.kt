package dev.timatifey.harmony.screen.auth.signin

import dev.timatifey.harmony.R
import dev.timatifey.harmony.data.Resource
import dev.timatifey.harmony.data.Status
import dev.timatifey.harmony.data.model.harmony.Token
import dev.timatifey.harmony.common.mvp.MvpPresenter
import dev.timatifey.harmony.common.nav.BackPressDispatcher
import dev.timatifey.harmony.common.nav.app.AppScreenNavigator
import dev.timatifey.harmony.service.AuthService
import dev.timatifey.harmony.util.Validator
import kotlinx.coroutines.*

class SignInPresenter(
    private val appScreenNavigator: AppScreenNavigator,
    private val backPressDispatcher: BackPressDispatcher,
    private val authService: AuthService
) : MvpPresenter<SignInMvpView>, SignInMvpView.Listener {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private lateinit var view: SignInMvpView

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
        appScreenNavigator.navigateUp()
        return true
    }

    override fun onSignInBtnClicked(username: String, password: String) {
        coroutineScope.launch {
            view.showLoading()
            val result: Resource<Token> = authService.authHarmony(username, password)
            view.hideLoading()
            when (result.status) {
                is Status.Success -> {
                    appScreenNavigator.navigateToHomeScreen(result.data!!)
                }
                is Status.Error -> {
                    view.showError(R.string.auth_failed)
                }
            }
        }
    }

    override fun onForgotPasswordTvClicked() {
        appScreenNavigator.navigateToRecoveryFragment()
    }

    override fun onHaveNotAccTvClicked() {
        appScreenNavigator.navigateToSignUpFragment()
    }

    override fun onSpotifyAuthBtnClicked() {
        appScreenNavigator.navigateToSpotifyAuthWebView()
    }

    override fun onUsernameFieldTextChanged(text: String) {
        if (!Validator.isUserNameValid(text)) {
            view.showUsernameFieldError(R.string.invalid_username)
        } else {
            view.hideUsernameFieldError()
        }
    }

    override fun onPasswordFieldTextChanged(text: String) {
        if (!Validator.isPasswordValid(text)) {
            view.showPasswordFieldError(R.string.invalid_password)
        } else {
            view.hidePasswordFieldError()
        }
    }
}