package dev.timatifey.harmony.screen.activity

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import dev.timatifey.harmony.R
import dev.timatifey.harmony.common.mvp.MvpViewObservableBase
import javax.inject.Inject

class MainMvpViewImpl @Inject constructor(
    layoutInflater: LayoutInflater
): MvpViewObservableBase<MainMvpView.Listener>(), MainMvpView {

    @SuppressLint("InflateParams")
    override var rootView: View = layoutInflater.inflate(R.layout.activity_main, null, true)

    private val drawer: DrawerLayout = findViewById(R.id.drawer_layout)
    private val navigationView: NavigationView = findViewById(R.id.activity_main__navigationView)

    init {
        navigationView.setNavigationItemSelectedListener { item ->
            listeners.forEach { it.onNavigationItemSelected(item) }
            true
        }
        lockDrawer()
    }

    override fun closeDrawer() {
        drawer.closeDrawer(GravityCompat.START)
    }

    override fun openDrawer() {
        drawer.openDrawer(GravityCompat.START)
    }

    override fun aboutUs() {
        Toast.makeText(context, "About us", Toast.LENGTH_SHORT).show()
    }

    override fun unlockDrawer() {
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }

    override fun lockDrawer() {
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    override fun drawerIsOpen(): Boolean =
        drawer.isDrawerOpen(GravityCompat.START)

}