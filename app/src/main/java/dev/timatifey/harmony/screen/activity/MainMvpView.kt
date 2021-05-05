package dev.timatifey.harmony.screen.activity

import android.view.MenuItem
import dev.timatifey.harmony.common.mvp.MvpViewObservable
import dev.timatifey.harmony.common.nav.BackPressedListener


interface MainMvpView: MvpViewObservable<MainMvpView.Listener>, DrawerController {

    interface Listener : BackPressedListener {
        fun onNavigationItemSelected(item: MenuItem): Boolean
    }

    fun aboutUs()
    fun drawerIsOpen(): Boolean
}