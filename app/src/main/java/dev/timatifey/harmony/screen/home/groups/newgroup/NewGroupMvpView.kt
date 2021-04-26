package dev.timatifey.harmony.screen.home.groups.newgroup

import android.net.Uri
import dev.timatifey.harmony.common.mvp.MvpViewObservable
import dev.timatifey.harmony.common.nav.BackPressedListener

interface NewGroupMvpView: MvpViewObservable<NewGroupMvpView.Listener> {
    interface Listener: BackPressedListener {
        fun onCreateBtnClicked(name: String, description: String, imageUri: String?)
        fun onCancelBtnClicked()
        fun onPickImageBtnClicked()
    }
    fun selectPhoto(selectedImage: Uri?)
}