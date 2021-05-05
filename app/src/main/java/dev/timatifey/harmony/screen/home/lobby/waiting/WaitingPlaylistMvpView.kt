package dev.timatifey.harmony.screen.home.lobby.waiting

import dev.timatifey.harmony.common.mvp.MvpViewObservable

interface WaitingPlaylistMvpView: MvpViewObservable<WaitingPlaylistMvpView.Listener> {
    interface Listener {
        fun onNotReadyBtnClicked()
    }
}