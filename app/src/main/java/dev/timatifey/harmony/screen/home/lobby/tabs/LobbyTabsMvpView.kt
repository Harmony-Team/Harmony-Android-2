package dev.timatifey.harmony.screen.home.lobby.tabs

import dev.timatifey.harmony.common.mvp.MvpViewObservable

interface LobbyTabsMvpView : MvpViewObservable<LobbyTabsMvpView.Listener>, LobbyTabsPageController {
    interface Listener {
        fun onSearchIconClicked()
    }
}

interface LobbyTabsPageController {
    fun setMusicPage()
    fun setPlaylistsPage()
}