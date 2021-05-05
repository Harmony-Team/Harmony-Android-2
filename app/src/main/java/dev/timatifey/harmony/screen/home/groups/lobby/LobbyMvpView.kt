package dev.timatifey.harmony.screen.home.groups.lobby

import dev.timatifey.harmony.common.mvp.MvpViewObservable
import dev.timatifey.harmony.common.nav.BackPressedListener
import dev.timatifey.harmony.data.model.harmony.HarmonyGroup
import dev.timatifey.harmony.data.model.harmony.HarmonyGroupUser
import dev.timatifey.harmony.data.model.harmony.HarmonyLobbyItem

interface LobbyMvpView: MvpViewObservable<LobbyMvpView.Listener> {
    interface Listener : BackPressedListener {
        fun onItemClicked(item: HarmonyLobbyItem)
        fun onBackBtnClicked()
        fun onSettingsBtnClicked()
    }

    fun bindData(users: List<HarmonyLobbyItem>)

    fun showMessage(msg: String)
    fun showMessage(msgId: Int)

}