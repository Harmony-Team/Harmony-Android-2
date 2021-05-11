package dev.timatifey.harmony.screen.home.lobby.waiting

import android.util.Log
import dev.timatifey.harmony.common.mvp.MvpPresenter
import dev.timatifey.harmony.common.nav.lobby.LobbyFragmentNavigator
import dev.timatifey.harmony.repo.lobby.LobbyState
import dev.timatifey.harmony.service.LobbyService
import kotlinx.coroutines.*

class WaitingPlaylistPresenter(
    private val lobbyFragmentNavigator: LobbyFragmentNavigator,
    private val lobbyService: LobbyService,
): MvpPresenter<WaitingPlaylistMvpView>, WaitingPlaylistMvpView.Listener {

    private lateinit var view: WaitingPlaylistMvpView
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    override fun bindView(view: WaitingPlaylistMvpView) {
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

    override fun onNotReadyBtnClicked() {
        Log.e("WaitingPResenter", "clicked")
        coroutineScope.launch {
            lobbyService.sendState(LobbyState.Unready)
            lobbyFragmentNavigator.toUnreadyStateFragment()
        }
    }
}