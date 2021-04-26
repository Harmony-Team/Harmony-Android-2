package dev.timatifey.harmony.screen.home.groups.joingroup

import dev.timatifey.harmony.common.mvp.MvpViewObservable
import dev.timatifey.harmony.common.nav.BackPressedListener

interface JoinGroupMvpView: MvpViewObservable<JoinGroupMvpView.Listener> {
    interface Listener: BackPressedListener {

    }
}