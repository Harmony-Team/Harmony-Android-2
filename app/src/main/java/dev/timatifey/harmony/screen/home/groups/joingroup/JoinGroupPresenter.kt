package dev.timatifey.harmony.screen.home.groups.joingroup

import dev.timatifey.harmony.common.mvp.MvpPresenter
import dev.timatifey.harmony.common.nav.AppScreenNavigator
import dev.timatifey.harmony.common.nav.BackPressDispatcher
import dev.timatifey.harmony.data.Status
import dev.timatifey.harmony.service.GroupService
import kotlinx.coroutines.*

class JoinGroupPresenter(
    private val appScreenNavigator: AppScreenNavigator,
    private val backPressDispatcher: BackPressDispatcher,
    private val groupService: GroupService,
) : MvpPresenter<JoinGroupMvpView>, JoinGroupMvpView.Listener {

    private lateinit var view: JoinGroupMvpView
    private val coroutineScope = CoroutineScope(Dispatchers.Main.immediate + SupervisorJob())

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
        coroutineScope.coroutineContext.cancelChildren()
    }

    override fun onJoinBtnClicked(code: String) {
        coroutineScope.launch {
            val groupRes = groupService.joinGroup(code)
            if (groupRes.status is Status.Success) {
//                appScreenNavigator.toGroupList()
                appScreenNavigator.toLobby(groupRes.data!!.id)
            } else {
                appScreenNavigator.toGroupList()
            }
        }
    }

    override fun onCancelBtnClicked() {
        appScreenNavigator.navigateUp()
    }

    override fun onBackPressed(): Boolean {
        appScreenNavigator.navigateUp()
        return true
    }
}