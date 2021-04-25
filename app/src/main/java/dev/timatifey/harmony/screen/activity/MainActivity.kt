package dev.timatifey.harmony.screen.activity

import android.os.Bundle
import android.util.Log
import dev.timatifey.harmony.R
import dev.timatifey.harmony.common.base.BaseActivity
import dev.timatifey.harmony.common.mvp.factory.MvpViewFactory
import dev.timatifey.harmony.common.mvp.factory.PresenterFactory
import javax.inject.Inject

class MainActivity : BaseActivity(), DrawerDispatcher {

    private lateinit var presenter: MainPresenter
    private lateinit var view: MainMvpView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Harmony)
        view = mvpViewFactory.createMainMvpView()
        setContentView(view.rootView)
        presenter = presenterFactory.createMainPresenter()
        presenter.bindView(view)
        presenter.bindDrawerDispatcher(this)
        if (appScreenNavigator.appSettings.isAuthorized) {
            unlockDrawer()
        } else {
            lockDrawer()
        }
    }

    override fun onStart() {
        presenter.onStart()
        super.onStart()
    }

    override fun onStop() {
        presenter.onStop()
        super.onStop()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun lockDrawer() {
        view.lockDrawer()
    }

    override fun unlockDrawer() {
        view.unlockDrawer()
    }

    override fun openDrawer() {
        view.openDrawer()
    }

    override fun closeDrawer() {
        view.closeDrawer()
    }

}