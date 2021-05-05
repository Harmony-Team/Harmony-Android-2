package dev.timatifey.harmony.screen.home.groups.lobby

import android.util.Log
import dev.timatifey.harmony.common.mvp.MvpPresenter
import dev.timatifey.harmony.common.nav.AppScreenNavigator
import dev.timatifey.harmony.common.nav.BackPressDispatcher
import dev.timatifey.harmony.data.Status
import dev.timatifey.harmony.data.model.harmony.AddUserBtn
import dev.timatifey.harmony.data.model.harmony.HarmonyGroupUser
import dev.timatifey.harmony.data.model.harmony.HarmonyLobbyItem
import dev.timatifey.harmony.screen.RequireDrawerDispatcher
import dev.timatifey.harmony.screen.activity.DrawerDispatcher
import dev.timatifey.harmony.service.GroupService
import kotlinx.coroutines.*

class LobbyPresenter(
    private val groupService: GroupService,
    private val groupId: Long,
    private val backPressDispatcher: BackPressDispatcher,
    private val appScreenNavigator: AppScreenNavigator,
): MvpPresenter<LobbyMvpView>, LobbyMvpView.Listener, RequireDrawerDispatcher {

    private lateinit var view: LobbyMvpView
    private lateinit var drawer: DrawerDispatcher

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private val users = mutableListOf<HarmonyGroupUser>()

    override fun bindView(view: LobbyMvpView) {
        this.view = view
        initUsers()
    }

    private fun initUsers() {
        coroutineScope.launch {
            val groupResource = groupService.getGroupById(groupId)
            if (groupResource.status is Status.Success) {
                users.clear()
                val userList = groupResource.data!!.users
                Log.e("LobbyPresenter", userList.toString())
                users.addAll(userList)
                view.bindData(users)
            } else {
                view.showMessage("GROUPS ERROR") //TODO("handle error groups")
            }
        }
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

    override fun onItemClicked(item: HarmonyLobbyItem) {
        when(item) {
            is AddUserBtn -> {
                view.showMessage("Add user")
            }
            is HarmonyGroupUser -> {
                view.showMessage("Clicked on ${item.login}")
            }
        }
    }

    override fun onBackBtnClicked() {
        appScreenNavigator.navigateUp()
    }

    override fun onSettingsBtnClicked() {
        view.showMessage("Settings clicked")
    }

    override fun onBackPressed(): Boolean {
        appScreenNavigator.navigateUp()
        return true
    }

    override fun bindDrawerDispatcher(drawer: DrawerDispatcher) {
        this.drawer = drawer
    }
}