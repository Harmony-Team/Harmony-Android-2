package dev.timatifey.harmony.screen.home.groups.grouplist

import dev.timatifey.harmony.common.mvp.MvpViewObservable
import dev.timatifey.harmony.common.nav.BackPressedListener

interface GroupListMvpView: MvpViewObservable<GroupListMvpView.Listener> {
    interface Listener : BackPressedListener {

    }
}