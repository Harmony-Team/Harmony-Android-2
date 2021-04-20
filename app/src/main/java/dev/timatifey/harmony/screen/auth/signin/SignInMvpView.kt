package dev.timatifey.harmony.screen.auth.signin

import dev.timatifey.harmony.common.mvp.MvpViewObservable
import dev.timatifey.harmony.common.nav.BackPressedListener

interface SignInMvpView : MvpViewObservable<SignInMvpView.Listener> {

    interface Listener : BackPressedListener {
        fun onSignInBtnClicked(username: String, password: String)
        fun onForgotPasswordTvClicked()
        fun onHaveNotAccTvClicked()
        fun onSpotifyAuthBtnClicked()
        fun onUsernameFieldTextChanged(text: String)
        fun onPasswordFieldTextChanged(text: String)
    }

    fun showLoading()
    fun hideLoading()

    fun showError(msgId: Int)
    fun showUsernameFieldError(msgId: Int)
    fun showPasswordFieldError(msgId: Int)
    fun hideUsernameFieldError()
    fun hidePasswordFieldError()
}