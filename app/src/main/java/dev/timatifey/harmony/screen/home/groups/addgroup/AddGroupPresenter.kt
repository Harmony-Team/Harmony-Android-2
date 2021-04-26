package dev.timatifey.harmony.screen.home.groups.addgroup

import dev.timatifey.harmony.common.mvp.MvpPresenter
import dev.timatifey.harmony.common.nav.AppScreenNavigator
import dev.timatifey.harmony.common.nav.BackPressDispatcher

class AddGroupPresenter(
    private val backPressDispatcher: BackPressDispatcher,
    private val appScreenNavigator: AppScreenNavigator,
) : MvpPresenter<AddGroupMvpView>, AddGroupMvpView.Listener {
    private lateinit var view: AddGroupMvpView

    override fun bindView(view: AddGroupMvpView) {
        this.view = view
    }

    override fun onStart() {
        view.registerListener(this)
        backPressDispatcher.registerListener(this)
    }

    override fun onStop() {
        view.unregisterListener(this)
        backPressDispatcher.unregisterListener(this)
    }

    override fun onDestroy() {
    }

    override fun onNewGroupClicked() {
        appScreenNavigator.toNewGroup()
    }

    override fun onJoinGroupClicked() {
        appScreenNavigator.toJoinGroup()
    }

    override fun onCancelClicked() {
        appScreenNavigator.navigateUp()
    }

    override fun onBackPressed(): Boolean {
        appScreenNavigator.navigateUp()
        return true
    }
}