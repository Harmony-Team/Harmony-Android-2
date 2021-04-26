package dev.timatifey.harmony.screen.home.groups.joingroup

import dev.timatifey.harmony.common.mvp.MvpPresenter
import dev.timatifey.harmony.common.nav.AppScreenNavigator
import dev.timatifey.harmony.common.nav.BackPressDispatcher
import dev.timatifey.harmony.service.GroupService

class JoinGroupPresenter(
    private val appScreenNavigator: AppScreenNavigator,
    private val backPressDispatcher: BackPressDispatcher,
    private val groupService: GroupService,
) : MvpPresenter<JoinGroupMvpView>, JoinGroupMvpView.Listener {

    private lateinit var view: JoinGroupMvpView

    override fun bindView(view: JoinGroupMvpView) {
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

    override fun onJoinBtnClicked(code: String) {
        val groupId = 1L //TODO("get group id by code")
        appScreenNavigator.toLobby(groupId)
    }

    override fun onCancelBtnClicked() {
        appScreenNavigator.navigateUp()
    }

    override fun onBackPressed(): Boolean {
        appScreenNavigator.navigateUp()
        return true
    }
}