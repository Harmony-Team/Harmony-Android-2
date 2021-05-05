package dev.timatifey.harmony.screen.activity

import android.os.Bundle
import dev.timatifey.harmony.R
import dev.timatifey.harmony.common.base.BaseActivity

class MainActivity : BaseActivity() {

    private lateinit var presenter: MainPresenter
    private lateinit var view: MainMvpView
    lateinit var drawerController: DrawerController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Harmony)
        view = mvpViewFactory.createMainMvpView()
        drawerController = view
        setContentView(view.rootView)
        presenter = presenterFactory.createMainPresenter()
        presenter.bindView(view)
        presenter.bindDrawerDispatcher(drawerController)
        if (appScreenNavigator.appSettings.isAuthorized) {
            drawerController.unlockDrawer()
        } else {
            drawerController.lockDrawer()
        }
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

}