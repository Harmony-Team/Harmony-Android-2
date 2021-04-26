package dev.timatifey.harmony.screen.home.groups.addgroup

import dev.timatifey.harmony.common.mvp.MvpViewObservable
import dev.timatifey.harmony.common.nav.BackPressedListener

interface AddGroupMvpView: MvpViewObservable<AddGroupMvpView.Listener> {
    interface Listener: BackPressedListener {
        fun onNewGroupClicked()
        fun onJoinGroupClicked()
        fun onCancelClicked()
    }
}