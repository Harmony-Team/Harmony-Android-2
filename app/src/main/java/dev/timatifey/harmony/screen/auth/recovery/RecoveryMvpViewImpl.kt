package dev.timatifey.harmony.screen.auth.recovery

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

class RecoveryMvpViewImpl(
    inflater: LayoutInflater,
    parent: ViewGroup?
) : MvpViewObservableBase<RecoveryMvpView.Listener>(), RecoveryMvpView {
    override var rootView: View = inflater.inflate(R.layout.fragment_recovery, parent, false)

    private val pbLoading: ProgressBar = findViewById(R.id.recovery__loading)
    private val etEmail: AppCompatEditText = findViewById(R.id.recovery__email)
    private val ibRecovery: AppCompatImageButton = findViewById(R.id.recovery__recovery_btn)
    private val tvHaveNotAcc: AppCompatTextView = findViewById(R.id.recovery__registration)

    init {
        etEmail.doAfterTextChanged {
            listeners.forEach {
                it.onEmailFieldTextChanged(etEmail.text.toString())
            }
        }
        ibRecovery.setOnClickListener {
            listeners.forEach {
                it.onRecoveryBtnClicked(
                    etEmail.text.toString()
                )
            }
        }
        tvHaveNotAcc.setOnClickListener { listeners.forEach { it.onHaveNotAccTvClicked() } }
    }

    override fun showLoading() {
        pbLoading.visibility = View.VISIBLE
        etEmail.isEnabled = false
        ibRecovery.isEnabled = false
        tvHaveNotAcc.isEnabled = false
    }

    override fun hideLoading() {
        pbLoading.visibility = View.GONE
        etEmail.isEnabled = true
        ibRecovery.isEnabled = true
        tvHaveNotAcc.isEnabled = true
    }

    override fun showError(msgId: Int) {
        showToast(getString(msgId))
    }

    override fun showEmailFieldError(msgId: Int) {
        etEmail.error = getString(msgId)
    }

    override fun hideEmailFieldError() {
        etEmail.error = null
    }

}