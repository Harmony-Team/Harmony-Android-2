package dev.timatifey.harmony.screen.home.lobby.tabs.tracks

import dev.timatifey.harmony.common.mvp.MvpPresenter
import dev.timatifey.harmony.common.nav.lobby.LobbyFragmentNavigator
import dev.timatifey.harmony.data.Status
import dev.timatifey.harmony.data.mappers.toSpotifyLobbyTrack
import dev.timatifey.harmony.data.model.harmony.User
import dev.timatifey.harmony.data.model.spotify.SpotifyLobbyTrack
import dev.timatifey.harmony.repo.lobby.LobbyProvider
import dev.timatifey.harmony.repo.lobby.LobbyState
import dev.timatifey.harmony.screen.home.lobby.tabs.LobbyTabsPageController
import dev.timatifey.harmony.screen.home.lobby.tabs.tracks.row.TracksRowMvpView
import dev.timatifey.harmony.service.LobbyService
import dev.timatifey.harmony.service.TracksService
import dev.timatifey.harmony.service.UserService
import kotlinx.coroutines.*

class TracksPresenter(
    private val lobbyProvider: LobbyProvider,
    private val lobbyFragmentNavigator: LobbyFragmentNavigator,
    private val pageController: LobbyTabsPageController,
    private val tracksService: TracksService,
    private val lobbyService: LobbyService,
) : MvpPresenter<TracksMvpView>, TracksMvpView.Listener {

    private lateinit var view: TracksMvpView
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    override fun bindView(view: TracksMvpView) {
        this.view = view
        initTrackList()
    }

    private fun initTrackList() {
        coroutineScope.launch {
            view.showLoading()
            val tracks = tracksService.getTracks()
            if (tracks.status is Status.Success) {
                view.bindData(tracks.data!!.map {
                    it.toSpotifyLobbyTrack(isSelected = false, isPlaying = false)
                })
            } else {
                view.showSnackbar("Get tracks error ${tracks.message.message}")
            }
            view.hideLoading()
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
            lobbyProvider.state = LobbyState.Waiting
            lobbyService.sendState(LobbyState.Waiting)
            if (lobbyProvider.user!!.username != lobbyProvider.hostLogin) {
                lobbyFragmentNavigator.toReadyStateFragment()
            } else {
                view.showNotReadyBtn()
                pageController.setPlaylistsPage()
            }
        }
    }

    override fun onNotReadyBtnClicked() {
        coroutineScope.launch {
            lobbyProvider.state = LobbyState.Unready
            lobbyService.sendState(LobbyState.Unready)
            view.showReadyBtn()
        }
    }

    override fun onTrackClicked(track: SpotifyLobbyTrack) {
        view.showSnackbar("Play ${track.name}")
        track.isPlaying = true
        coroutineScope.launch {
            delay(3000)
            track.isPlaying = false
        }
    }

    override fun onTrackCheckBtnClicked(track: SpotifyLobbyTrack) {
        track.isSelected = track.isSelected.not()
        if (track.isSelected) {
            coroutineScope.launch {
//                lobbyService.addTrack(track.id.toLong(), lobbyProvider.groupId!!)
            }
        } else {
            coroutineScope.launch {
//                lobbyService.removeTrack(track.id.toLong(), lobbyProvider.groupId!!)
            }
        }
    }
}