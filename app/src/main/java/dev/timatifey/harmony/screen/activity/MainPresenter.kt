package dev.timatifey.harmony.screen.activity

import android.view.MenuItem
import dev.timatifey.harmony.R
import dev.timatifey.harmony.common.mvp.MvpPresenter
import dev.timatifey.harmony.common.nav.AppScreenNavigator

class MainPresenter(
    private val appScreenNavigator: AppScreenNavigator,
) : MvpPresenter<MainMvpView>, MainMvpView.Listener {

    private lateinit var view: MainMvpView
    private lateinit var drawerLocker: DrawerLocker

    override fun bindView(view: MainMvpView) {
        this.view = view
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu__profile -> appScreenNavigator.toProfile()
            R.id.menu__groups -> appScreenNavigator.toGroupList()
            R.id.menu__settings -> appScreenNavigator.toSettings()
            R.id.menu__about_us -> view.aboutUs()
            R.id.menu__logout -> {
                drawerLocker.lockDrawer()
                appScreenNavigator.toAuth()
            }
        }
        view.closeDrawer()
        return true
    }

    override fun onBackPressed(): Boolean =
        when {
            view.drawerIsOpen() -> {
                view.closeDrawer()
                true
            }
            else -> false
        }

    override fun onStart() {
        view.registerListener(this)
    }

    override fun onStop() {
        view.unregisterListener(this)
    }

    override fun onDestroy() {
    }

    override fun bindDrawerLocker(drawerLocker: DrawerLocker) {
        this.drawerLocker = drawerLocker
    }

}