package dev.timatifey.harmony.screen.home.groups.newgroup

import dev.timatifey.harmony.common.mvp.MvpViewObservable
import dev.timatifey.harmony.common.nav.BackPressedListener

interface NewGroupMvpView: MvpViewObservable<NewGroupMvpView.Listener> {
    interface Listener: BackPressedListener {

    }
}