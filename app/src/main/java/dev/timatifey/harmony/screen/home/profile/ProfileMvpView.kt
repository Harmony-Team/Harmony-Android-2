package dev.timatifey.harmony.screen.home.profile

import dev.timatifey.harmony.common.mvp.MvpViewObservable
import dev.timatifey.harmony.common.nav.BackPressedListener

interface ProfileMvpView: MvpViewObservable<ProfileMvpView.Listener> {
    interface Listener : BackPressedListener {

    }
}