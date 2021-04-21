package dev.timatifey.harmony.screen.activity

import android.view.MenuItem
import dev.timatifey.harmony.common.mvp.MvpViewObservable
import dev.timatifey.harmony.common.nav.BackPressedListener


interface MainMvpView: MvpViewObservable<MainMvpView.Listener> {

    interface Listener : BackPressedListener {
        fun onNavigationItemSelected(item: MenuItem): Boolean
    }

    fun closeDrawer()
    fun openDrawer()
    fun aboutUs()

    fun unlockDrawer()
    fun lockDrawer()

    fun drawerIsOpen(): Boolean
}