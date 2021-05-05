package dev.timatifey.harmony.screen.auth.signin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.doAfterTextChanged
import dev.timatifey.harmony.R
import dev.timatifey.harmony.common.mvp.MvpViewObservableBase

class SignInMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : MvpViewObservableBase<SignInMvpView.Listener>(), SignInMvpView {

    override var rootView: View =
        layoutInflater.inflate(R.layout.fragment_sign_in, parent, false)

    private val pbLoading: ProgressBar = findViewById(R.id.fragment_signin__loading)
    private val etUsername: AppCompatEditText = findViewById(R.id.fragment_signin__username)
    private val etPassword: AppCompatEditText = findViewById(R.id.fragment_signin__password)
    private val tvForgotPsw: AppCompatTextView = findViewById(R.id.fragment_signin__forgot_password_btn)
    private val ibSpotifyAuth: AppCompatImageButton = findViewById(R.id.fragment_signin__spotify_btn)
    private val ibSignIn: AppCompatImageButton = findViewById(R.id.fragment_signin__login_btn)
    private val tvHaveNotAcc: AppCompatTextView = findViewById(R.id.fragment_signin__registration_btn)

    init {
        etUsername.doAfterTextChanged {
            listeners.forEach {
                it.onUsernameFieldTextChanged(etUsername.text.toString())
            }
        }
        etPassword.doAfterTextChanged {
            listeners.forEach {
                it.onPasswordFieldTextChanged(etPassword.text.toString())
            }
        }
        tvForgotPsw.setOnClickListener { listeners.forEach { it.onForgotPasswordTvClicked() } }
        ibSpotifyAuth.setOnClickListener { listeners.forEach { it.onSpotifyAuthBtnClicked() } }
        ibSignIn.setOnClickListener {
            listeners.forEach {
                it.onSignInBtnClicked(
                    etUsername.text.toString(),
                    etPassword.text.toString()
                )
            }
        }
        tvHaveNotAcc.setOnClickListener { listeners.forEach { it.onHaveNotAccTvClicked() } }
    }

    override fun showLoading() {
        pbLoading.visibility = View.VISIBLE
        etUsername.isEnabled = false
        etPassword.isEnabled = false
        tvForgotPsw.isEnabled = false
        ibSpotifyAuth.isEnabled = false
        tvHaveNotAcc.isEnabled = false
        ibSignIn.isEnabled = false
    }

    override fun hideLoading() {
        pbLoading.visibility = View.GONE
        etUsername.isEnabled = true
        etPassword.isEnabled = true
        tvForgotPsw.isEnabled = true
        ibSpotifyAuth.isEnabled = true
        tvHaveNotAcc.isEnabled = true
        ibSignIn.isEnabled = true
    }

    override fun showError(msgId: Int) {
        showToast(getString(msgId))
    }

    override fun showUsernameFieldError(msgId: Int) {
        etUsername.error = getString(msgId)
    }

    override fun showPasswordFieldError(msgId: Int) {
        etPassword.error = getString(msgId)
    }

    override fun hideUsernameFieldError() {
        etUsername.error = null
    }

    override fun hidePasswordFieldError() {
        etPassword.error = null
    }
}