package dev.timatifey.harmony.screen.auth.signup

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.doAfterTextChanged
import dev.timatifey.harmony.R
import dev.timatifey.harmony.common.mvp.MvpViewObservableBase

class SignUpMvpViewImpl(
    inflater: LayoutInflater,
    parent: ViewGroup?
) : MvpViewObservableBase<SignUpMvpView.Listener>(), SignUpMvpView {
    override var rootView: View = inflater.inflate(R.layout.fragment_sign_up, parent, false)

    private val pbLoading: ProgressBar = findViewById(R.id.register__loading)
    private val etUsername: AppCompatEditText = findViewById(R.id.register__username)
    private val etPassword: AppCompatEditText = findViewById(R.id.register__password)
    private val etEmail: AppCompatEditText = findViewById(R.id.register__email)
    private val ibSignUp: AppCompatImageButton = findViewById(R.id.register__register_btn)
    private val tvHaveAcc: AppCompatTextView = findViewById(R.id.register__already_have_an_acc)

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
        etEmail.doAfterTextChanged {
            listeners.forEach {
                it.onEmailFieldTextChanged(etEmail.text.toString())
            }
        }
        ibSignUp.setOnClickListener {
            listeners.forEach {
                it.onSignUpBtnClicked(
                    etUsername.text.toString(),
                    etEmail.text.toString(),
                    etPassword.text.toString()
                )
            }
        }
        tvHaveAcc.setOnClickListener { listeners.forEach { it.onHaveAccTvClicked() } }
    }

    override fun showLoading() {
        pbLoading.visibility = View.VISIBLE
        etUsername.isEnabled = false
        etPassword.isEnabled = false
        etEmail.isEnabled = false
        tvHaveAcc.isEnabled = false
        ibSignUp.isEnabled = false
    }

    override fun hideLoading() {
        pbLoading.visibility = View.GONE
        etUsername.isEnabled = true
        etPassword.isEnabled = true
        etEmail.isEnabled = true
        tvHaveAcc.isEnabled = true
        ibSignUp.isEnabled = true
    }

    override fun showError(msgId: Int) {
        showToast(getString(msgId))
    }

    override fun showUsernameFieldError(msgId: Int) {
        etUsername.error = getString(msgId)
    }

    override fun showEmailFieldError(msgId: Int) {
        etEmail.error = getString(msgId)
    }

    override fun showPasswordFieldError(msgId: Int) {
        etPassword.error = getString(msgId)
    }

    override fun hideUsernameFieldError() {
        etUsername.error = null
    }

    override fun hideEmailFieldError() {
        etEmail.error = null
    }

    override fun hidePasswordFieldError() {
        etPassword.error = null
    }

}