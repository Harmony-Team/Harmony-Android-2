package dev.timatifey.harmony.screen.activity

import dev.timatifey.harmony.common.mvp.MvpViewObservable
import dev.timatifey.harmony.common.nav.BackPressedListener

interface MainMvpView : MvpViewObservable<MainMvpView.Listener>, MenuController {

    interface Listener : BackPressedListener {
        fun onItemSelected(position: Int)
    }

    fun drawerIsOpen(): Boolean
}