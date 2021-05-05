package dev.timatifey.harmony.screen.home.lobby

import android.util.Log
import dev.timatifey.harmony.common.mvp.MvpPresenter
import dev.timatifey.harmony.common.nav.app.AppScreenNavigator
import dev.timatifey.harmony.common.nav.BackPressDispatcher
import dev.timatifey.harmony.common.nav.lobby.LobbyFragmentNavigator
import dev.timatifey.harmony.data.Status
import dev.timatifey.harmony.data.model.harmony.AddUserBtn
import dev.timatifey.harmony.data.model.harmony.HarmonyGroupUser
import dev.timatifey.harmony.data.model.harmony.HarmonyLobbyItem
import dev.timatifey.harmony.lobby.LobbyState
import dev.timatifey.harmony.lobby.LobbyStateMachine
import dev.timatifey.harmony.screen.home.group.share.ShareIntentListener
import dev.timatifey.harmony.service.GroupService
import dev.timatifey.harmony.service.LobbyService
import kotlinx.coroutines.*

class LobbyPresenter(
    private val groupId: Long,

    private val groupService: GroupService,
    private val lobbyService: LobbyService,

    private val backPressDispatcher: BackPressDispatcher,
    private val appScreenNavigator: AppScreenNavigator,
    private val listenerShare: ShareIntentListener,
) : MvpPresenter<LobbyMvpView>, LobbyMvpView.Listener {

    private lateinit var view: LobbyMvpView

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
        when (item) {
            is AddUserBtn -> {
                coroutineScope.launch {
                    val code = lobbyService.generateShareCode(groupId)
                    if (code.status is Status.Success) {
                        listenerShare.startActivityForShare(code.data!!)
                    }
                }
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
}