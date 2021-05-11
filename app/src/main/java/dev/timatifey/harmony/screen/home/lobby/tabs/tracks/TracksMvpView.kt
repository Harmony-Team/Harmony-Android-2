package dev.timatifey.harmony.screen.home.lobby.tabs.tracks

import dev.timatifey.harmony.common.mvp.MvpViewObservable
import dev.timatifey.harmony.data.model.spotify.SpotifyLobbyTrack
import dev.timatifey.harmony.screen.home.lobby.tabs.tracks.row.TracksRowMvpView

interface TracksMvpView : MvpViewObservable<TracksMvpView.Listener> {
    interface Listener {
        fun onReadyBtnClicked()
        fun onNotReadyBtnClicked()
        fun onTrackClicked(track: SpotifyLobbyTrack)
        fun onTrackCheckBtnClicked(track: SpotifyLobbyTrack)
    }
    fun showReadyBtn()
    fun showNotReadyBtn()
    fun showLoading()
    fun hideLoading()
    fun showSnackbar(msg: String)
    fun showSnackbar(msgId: Int)
    fun bindData(tracks: List<SpotifyLobbyTrack>)
}