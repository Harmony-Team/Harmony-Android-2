package dev.timatifey.harmony.screen.home.lobby.row

import dev.timatifey.harmony.common.mvp.MvpView
import dev.timatifey.harmony.data.model.harmony.HarmonyLobbyItem

interface LobbyRowMvpView : MvpView {

    interface Listener {
        fun onItemClicked(item: HarmonyLobbyItem)
    }

    fun bindData(item: HarmonyLobbyItem)
}