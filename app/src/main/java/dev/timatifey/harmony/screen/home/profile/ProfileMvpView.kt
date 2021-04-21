package dev.timatifey.harmony.screen.home.profile

import android.net.Uri
import dev.timatifey.harmony.common.mvp.MvpViewObservable
import dev.timatifey.harmony.common.nav.BackPressedListener

interface ProfileMvpView: MvpViewObservable<ProfileMvpView.Listener> {
    interface Listener : BackPressedListener {
        fun onMenuBtnClicked()
    }

    fun setUsername(username: String)
    fun setImageProfile(imageUri: Uri)
    fun showMessage(msgId: Int)
}