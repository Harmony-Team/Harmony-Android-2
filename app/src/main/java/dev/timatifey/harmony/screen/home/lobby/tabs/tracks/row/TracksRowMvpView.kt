package dev.timatifey.harmony.screen.home.lobby.tabs.tracks.row

import dev.timatifey.harmony.common.mvp.MvpView
import dev.timatifey.harmony.data.model.spotify.SpotifyLobbyTrack

interface TracksRowMvpView: MvpView {
    interface Listener {
        fun onTrackClicked(trackView: TracksRowMvpView, track: SpotifyLobbyTrack)
        fun onCheckBtnClicked(trackView: TracksRowMvpView, track: SpotifyLobbyTrack)
    }

    fun bindData(track: SpotifyLobbyTrack)
    fun showPlayingImage()
    fun hidePlayingImage()
    fun isSelectedTrack(isSelected: Boolean)
}