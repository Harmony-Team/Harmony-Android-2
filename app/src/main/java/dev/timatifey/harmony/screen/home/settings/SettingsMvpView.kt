package dev.timatifey.harmony.screen.home.settings

import dev.timatifey.harmony.common.mvp.MvpViewObservable
import dev.timatifey.harmony.common.nav.BackPressedListener

interface SettingsMvpView: MvpViewObservable<SettingsMvpView.Listener> {
    interface Listener : BackPressedListener {

    }
}