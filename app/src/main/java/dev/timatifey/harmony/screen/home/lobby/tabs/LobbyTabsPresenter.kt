package dev.timatifey.harmony.screen.home.lobby.tabs

import dev.timatifey.harmony.common.mvp.MvpPresenter
import dev.timatifey.harmony.common.nav.lobby.LobbyFragmentNavigator
import dev.timatifey.harmony.data.Status
import dev.timatifey.harmony.data.model.harmony.User
import dev.timatifey.harmony.lobby.LobbyState
import dev.timatifey.harmony.service.LobbyService
import dev.timatifey.harmony.service.UserService
import kotlinx.coroutines.*

class LobbyTabsPresenter(
    private val userService: UserService,
    private val lobbyFragmentNavigator: LobbyFragmentNavigator,
    private val lobbyService: LobbyService,
) : MvpPresenter<LobbyTabsMvpView>, LobbyTabsMvpView.Listener {

    private lateinit var view: LobbyTabsMvpView
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    override fun bindView(view: LobbyTabsMvpView) {
        this.view = view
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

    override fun onSearchIconClicked() {
        //TODO("Search)
    }

}
