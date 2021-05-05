package dev.timatifey.harmony.screen.home.lobby.tabs.music

import android.util.Log
import dev.timatifey.harmony.common.mvp.MvpPresenter
import dev.timatifey.harmony.common.nav.lobby.LobbyFragmentNavigator
import dev.timatifey.harmony.data.Status
import dev.timatifey.harmony.data.model.harmony.User
import dev.timatifey.harmony.lobby.LobbyState
import dev.timatifey.harmony.screen.home.lobby.tabs.LobbyTabsPageController
import dev.timatifey.harmony.service.LobbyService
import dev.timatifey.harmony.service.UserService
import kotlinx.coroutines.*

class MusicPresenter(
    private val lobbyFragmentNavigator: LobbyFragmentNavigator,
    private val pageController: LobbyTabsPageController,
    private val lobbyService: LobbyService,
    private val userService: UserService,
): MvpPresenter<MusicMvpView>, MusicMvpView.Listener {

    private lateinit var view: MusicMvpView
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private lateinit var user: User
    private lateinit var hostLogin: String

    override fun bindView(view: MusicMvpView) {
        this.view = view
        initUser()
    }

    private fun initUser() {
        coroutineScope.launch {
            val res = userService.getUser()
            if (res.status is Status.Success) {
                user = res.data!!
            }
            val host = lobbyService.getHostLobbyByGroupId(lobbyFragmentNavigator.groupId)
            if (host.status is Status.Success) {
                hostLogin = host.data!!
            }
        }
    }

    override fun onStart() {
        view.registerListener(this)
    }

    override fun onStop() {
        view.unregisterListener(this)
    }

    override fun onDestroy() {
        coroutineScope.coroutineContext.cancelChildren()
    }

    override fun onReadyBtnClicked() {
        coroutineScope.launch {
            lobbyService.sendState(LobbyState.Waiting)
            if (user.username != hostLogin) {
                lobbyFragmentNavigator.toReadyStateFragment()
            } else {
                view.showNotReadyBtn()
                pageController.setPlaylistsPage()
            }
        }
    }

    override fun onNotReadyBtnClicked() {
        coroutineScope.launch {
            lobbyService.sendState(LobbyState.Unready)
            view.showReadyBtn()
        }
    }
}