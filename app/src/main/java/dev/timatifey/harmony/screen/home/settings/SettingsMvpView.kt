package dev.timatifey.harmony.screen.home.settings

import dev.timatifey.harmony.common.mvp.MvpViewObservable
import dev.timatifey.harmony.common.nav.BackPressedListener

interface SettingsMvpView: MvpViewObservable<SettingsMvpView.Listener> {
    interface Listener : BackPressedListener {
        fun onEmailEditClicked()
        fun onPasswordEditClicked()
        fun onSpotifySwitchClicked(isChecked: Boolean)
    }

    fun setEmailText(text: String)
    fun setPasswordText(text: String)
    fun setSpotifySwitchState(state: Boolean)

    fun showMessage(msg: String)
    fun showMessage(msgId: Int)
}