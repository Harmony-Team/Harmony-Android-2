package dev.timatifey.harmony.screen.home.lobby

import android.content.Intent
import android.util.Log
import dev.timatifey.harmony.common.mvp.MvpPresenter
import dev.timatifey.harmony.common.nav.app.AppScreenNavigator
import dev.timatifey.harmony.common.nav.BackPressDispatcher
import dev.timatifey.harmony.data.Status
import dev.timatifey.harmony.data.model.harmony.AddUserBtn
import dev.timatifey.harmony.data.model.harmony.HarmonyGroupUser
import dev.timatifey.harmony.data.model.harmony.HarmonyLobbyItem
import dev.timatifey.harmony.repo.lobby.LobbyProvider
import dev.timatifey.harmony.screen.home.group.share.ShareIntentListener
import dev.timatifey.harmony.service.GroupService
import dev.timatifey.harmony.service.LobbyService
import kotlinx.coroutines.*

class LobbyPresenter(
    private val lobbyProvider: LobbyProvider,

    private val groupService: GroupService,
    private val lobbyService: LobbyService,

    private val backPressDispatcher: BackPressDispatcher,
    private val appScreenNavigator: AppScreenNavigator,
    private val listenerShare: ShareIntentListener,
) : MvpPresenter<LobbyMvpView>, LobbyMvpView.Listener {

    private lateinit var view: LobbyMvpView

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    override fun bindView(view: LobbyMvpView) {
        this.view = view
        initUsers()
    }

    private fun initUsers() {
        coroutineScope.launch {
            val groupResource = groupService.getGroupById(lobbyProvider.groupId!!)
            if (groupResource.status is Status.Success) {
                lobbyProvider.users.clear()
                val userList = groupResource.data!!.users
                Log.e("LobbyPresenter", userList.toString())
                lobbyProvider.users.addAll(userList)
                view.bindData(lobbyProvider.users)
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
                    val code = lobbyService.generateShareCode(lobbyProvider.groupId!!)
                    if (code.status is Status.Success) {
                        view.isVisibleLoading(true)
                        view.disableAll()
                        listenerShare.startActivityForShare(code.data!!)
                    }
                }
            }
            is HarmonyGroupUser -> {
                view.showMessage("Clicked on ${item.login}")
            }
        }
    }

    fun onShareResult(resultCode: Int, data: Intent?) {
        view.isVisibleLoading(false)
        view.enableAll()
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