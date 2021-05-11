package dev.timatifey.harmony.screen.home.lobby.tabs.playlists

import dev.timatifey.harmony.common.mvp.MvpPresenter
import dev.timatifey.harmony.common.nav.app.AppScreenNavigator
import dev.timatifey.harmony.common.nav.lobby.LobbyFragmentNavigator
import dev.timatifey.harmony.data.Status
import dev.timatifey.harmony.data.model.harmony.User
import dev.timatifey.harmony.repo.lobby.LobbyProvider
import dev.timatifey.harmony.service.LobbyService
import dev.timatifey.harmony.service.UserService
import kotlinx.coroutines.*

class PlaylistsPresenter(
    private val lobbyProvider: LobbyProvider,
    private val lobbyFragmentNavigator: LobbyFragmentNavigator,
    private val lobbyService: LobbyService,
    private val userService: UserService,
    private val appScreenNavigator: AppScreenNavigator,
) : MvpPresenter<PlaylistsMvpView>, PlaylistsMvpView.Listener {

    private lateinit var view: PlaylistsMvpView
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    override fun bindView(view: PlaylistsMvpView) {
        this.view = view
        if (lobbyProvider.user!!.username != lobbyProvider.hostLogin) {
            view.hideCreateBtn()
        } else {
            view.showCreateBtn()
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