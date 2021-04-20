package dev.timatifey.harmony.screen.auth.recovery

import dev.timatifey.harmony.common.mvp.MvpViewObservable
import dev.timatifey.harmony.common.nav.BackPressedListener

interface RecoveryMvpView: MvpViewObservable<RecoveryMvpView.Listener> {
    interface Listener : BackPressedListener {
        fun onRecoveryBtnClicked(email: String)
        fun onHaveNotAccTvClicked()
        fun onEmailFieldTextChanged(text: String)
    }

    fun showLoading()
    fun hideLoading()

    fun showError(msgId: Int)
    fun showEmailFieldError(msgId: Int)
    fun hideEmailFieldError()
}