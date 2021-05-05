package dev.timatifey.harmony.screen.home.lobby.tabs.music

import dev.timatifey.harmony.common.mvp.MvpViewObservable

interface MusicMvpView : MvpViewObservable<MusicMvpView.Listener> {
    interface Listener {
        fun onReadyBtnClicked()
        fun onNotReadyBtnClicked()
    }
    fun showReadyBtn()
    fun showNotReadyBtn()
}