package dev.timatifey.harmony.screen.home.groups.grouplist.row

import dev.timatifey.harmony.common.mvp.MvpView
import dev.timatifey.harmony.data.model.harmony.HarmonyGroup

interface GroupListRowMvpView : MvpView {

    interface Listener {
        fun onGroupClicked(group: HarmonyGroup)
        fun onGroupSwiped(group: HarmonyGroup)
    }

    fun bindData(group: HarmonyGroup)
}