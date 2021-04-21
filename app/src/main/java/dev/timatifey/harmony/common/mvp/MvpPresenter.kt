package dev.timatifey.harmony.common.mvp

import dev.timatifey.harmony.screen.activity.DrawerLocker

interface MvpPresenter<T : MvpView> {
    fun bindView(view: T)
    fun bindDrawerLocker(drawerLocker: DrawerLocker)
    fun onStart()
    fun onStop()
    fun onDestroy()
}
