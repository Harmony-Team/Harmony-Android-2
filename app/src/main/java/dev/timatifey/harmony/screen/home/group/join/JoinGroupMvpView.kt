package dev.timatifey.harmony.screen.home.group.join

import dev.timatifey.harmony.common.mvp.MvpViewObservable
import dev.timatifey.harmony.common.nav.BackPressedListener

interface JoinGroupMvpView: MvpViewObservable<JoinGroupMvpView.Listener> {
    interface Listener: BackPressedListener {
        fun onJoinBtnClicked(code: String)
        fun onCancelBtnClicked()
    }
}