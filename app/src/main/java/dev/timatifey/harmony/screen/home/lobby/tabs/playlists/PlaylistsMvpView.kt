package dev.timatifey.harmony.screen.home.lobby.tabs.playlists

import dev.timatifey.harmony.common.mvp.MvpViewObservable

interface PlaylistsMvpView : MvpViewObservable<PlaylistsMvpView.Listener> {
    interface Listener {
        fun onCreateBtnClicked()
    }
    fun showCreateBtn()
    fun hideCreateBtn()
}