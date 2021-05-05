package dev.timatifey.harmony.screen.home.lobby.tabs.playlists

import dev.timatifey.harmony.common.mvp.MvpPresenter
import dev.timatifey.harmony.common.nav.app.AppScreenNavigator
import dev.timatifey.harmony.common.nav.lobby.LobbyFragmentNavigator
import dev.timatifey.harmony.data.Status
import dev.timatifey.harmony.data.model.harmony.User
import dev.timatifey.harmony.lobby.LobbyState
import dev.timatifey.harmony.screen.home.lobby.tabs.LobbyTabsPageController
import dev.timatifey.harmony.service.LobbyService
import dev.timatifey.harmony.service.UserService
import kotlinx.coroutines.*

class PlaylistsPresenter(
    private val lobbyFragmentNavigator: LobbyFragmentNavigator,
    private val lobbyService: LobbyService,
    private val userService: UserService,
    private val appScreenNavigator: AppScreenNavigator,
) : MvpPresenter<PlaylistsMvpView>, PlaylistsMvpView.Listener {

    private lateinit var view: PlaylistsMvpView
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private lateinit var user: User
    private lateinit var hostLogin: String

    override fun bindView(view: PlaylistsMvpView) {
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
            if (user.username != hostLogin) {
                view.hideCreateBtn()
            } else {
                view.showCreateBtn()
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

    override fun onCreateBtnClicked() {

    }
}