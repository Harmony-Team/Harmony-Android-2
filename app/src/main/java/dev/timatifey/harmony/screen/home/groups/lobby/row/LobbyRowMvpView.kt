package dev.timatifey.harmony.screen.home.groups.lobby.row

import dev.timatifey.harmony.common.mvp.MvpView
import dev.timatifey.harmony.data.model.harmony.HarmonyGroup
import dev.timatifey.harmony.data.model.harmony.HarmonyLobbyItem

interface LobbyRowMvpView : MvpView {

    interface Listener {
        fun onItemClicked(item: HarmonyLobbyItem)
    }

    fun bindData(item: HarmonyLobbyItem)
}