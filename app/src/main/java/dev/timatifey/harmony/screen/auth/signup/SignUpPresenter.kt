package dev.timatifey.harmony.screen.auth.signup

import dev.timatifey.harmony.R
import dev.timatifey.harmony.common.mvp.MvpPresenter
import dev.timatifey.harmony.common.nav.BackPressDispatcher
import dev.timatifey.harmony.common.nav.app.AppScreenNavigator
import dev.timatifey.harmony.data.Status
import dev.timatifey.harmony.service.AuthService
import dev.timatifey.harmony.util.Validator
import kotlinx.coroutines.*

class SignUpPresenter(
    private val appScreenNavigator: AppScreenNavigator,
    private val backPressDispatcher: BackPressDispatcher,
    private val authService: AuthService
) : MvpPresenter<SignUpMvpView>, SignUpMvpView.Listener {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private lateinit var view: SignUpMvpView

    override fun bindView(view: SignUpMvpView) {
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

    override fun onSignUpBtnClicked(username: String, email: String, password: String) {
        coroutineScope.launch {
            view.showLoading()
            val result = authService.registerHarmony(username, email, password)
            view.hideLoading()
            when (result.status) {
                is Status.Success -> {
                    appScreenNavigator.toHome(result.data!!)
                }
                is Status.Error -> {
                    view.showError(R.string.auth_failed)
                }
            }
        }
    }

    override fun onHaveAccTvClicked() {
        appScreenNavigator.toSignIn()
    }

    override fun onUsernameFieldTextChanged(text: String) {
        if (!Validator.isUserNameValid(text)) {
            view.showUsernameFieldError(R.string.invalid_username)
        } else {
            view.hideUsernameFieldError()
        }
    }

    override fun onEmailFieldTextChanged(text: String) {
        if (!Validator.isEmailValid(text)) {
            view.showEmailFieldError(R.string.invalid_username)
        } else {
            view.hidePasswordFieldError()
        }
    }

    override fun onPasswordFieldTextChanged(text: String) {
        if (!Validator.isPasswordValid(text)) {
            view.showPasswordFieldError(R.string.invalid_password)
        } else {
            view.hidePasswordFieldError()
        }
    }

    override fun onBackPressed(): Boolean {
        appScreenNavigator.navigateUp()
        return true
    }
}