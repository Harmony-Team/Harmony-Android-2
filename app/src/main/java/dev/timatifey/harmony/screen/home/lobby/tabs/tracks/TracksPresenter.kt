package dev.timatifey.harmony.screen.home.lobby.tabs.tracks

import dev.timatifey.harmony.common.mvp.MvpPresenter
import dev.timatifey.harmony.common.nav.lobby.LobbyFragmentNavigator
import dev.timatifey.harmony.data.Status
import dev.timatifey.harmony.data.mappers.toModel
import dev.timatifey.harmony.data.mappers.toSpotifyLobbyTrack
import dev.timatifey.harmony.data.model.spotify.SpotifyLobbyTrack
import dev.timatifey.harmony.repo.lobby.LobbyProvider
import dev.timatifey.harmony.repo.lobby.LobbyState
import dev.timatifey.harmony.screen.home.lobby.tabs.LobbyTabsPageController
import dev.timatifey.harmony.screen.home.lobby.tabs.tracks.row.TracksRowMvpView
import dev.timatifey.harmony.service.LobbyService
import dev.timatifey.harmony.service.TracksService
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
            val lobbyAddedTracks = lobbyService.getLobbyTracks(lobbyProvider.groupId!!)
            var listTrackId = listOf<String>()
            if (lobbyAddedTracks.status is Status.Success) {
                listTrackId = lobbyAddedTracks.data!!
                    .filter { it.username == lobbyProvider.user!!.username }
                    .map { it.spotifyTrackId }
            }
            lobbyProvider.trackIdList = listTrackId
            if (tracks.status is Status.Success) {
                view.bindData(tracks.data!!.map {
                    it.toSpotifyLobbyTrack(
                        isSelected = listTrackId.contains(it.id),
                        isPlaying = false
                    )
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
        tracksService.stopPlayAudio()
        view.stopLastPlayingTrack()
        view.unregisterListener(this)
    }

    override fun onDestroy() {
        coroutineScope.coroutineContext.cancelChildren()
    }

    override fun onReadyBtnClicked(selectedTracks: List<SpotifyLobbyTrack>) {
        coroutineScope.launch {
            lobbyProvider.state = LobbyState.Waiting
            lobbyService.sendState(LobbyState.Waiting)
            if (lobbyProvider.user!!.username != lobbyProvider.hostLogin) {
                lobbyFragmentNavigator.toReadyStateFragment()
            } else {
                pageController.setPlaylistsPage()
                delay(300)
                view.showNotReadyBtn()
            }
            tracksService.stopPlayAudio()
            view.stopLastPlayingTrack()
            for (track in selectedTracks) {
                lobbyService.addTrack(track.id, lobbyProvider.groupId!!)
            }
            val selectedIds = selectedTracks.map { it.id }
            for (id in lobbyProvider.trackIdList!!) {
                if (!selectedIds.contains(id)) {
                    lobbyService.removeTrack(id, lobbyProvider.groupId!!)
                }
            }
            lobbyProvider.trackIdList = selectedIds
        }
    }

    override fun onNotReadyBtnClicked(selectedTracks: List<SpotifyLobbyTrack>) {
        coroutineScope.launch {
            lobbyProvider.state = LobbyState.Unready
            lobbyService.sendState(LobbyState.Unready)
            view.showReadyBtn()
            for (track in selectedTracks) {
                lobbyService.removeTrack(track.id, lobbyProvider.groupId!!)
            }
        }
    }

    override fun onTrackClicked(trackView: TracksRowMvpView, track: SpotifyLobbyTrack) {
        coroutineScope.launch {
            if (track.isPlaying) {
                tracksService.playAudio(
                    track.toModel()
                ) { view.stopLastPlayingTrack() }
            } else {
                tracksService.stopPlayAudio()
            }
        }
    }
}