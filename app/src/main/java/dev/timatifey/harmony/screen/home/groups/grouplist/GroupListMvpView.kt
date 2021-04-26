package dev.timatifey.harmony.screen.home.groups.grouplist

import dev.timatifey.harmony.common.mvp.MvpViewObservable
import dev.timatifey.harmony.common.nav.BackPressedListener
import dev.timatifey.harmony.data.model.harmony.HarmonyGroup

interface GroupListMvpView: MvpViewObservable<GroupListMvpView.Listener> {
    interface Listener : BackPressedListener {
        fun onGroupClicked(group: HarmonyGroup)
        fun onMenuBtnClicked()
        fun onGroupSwiped(group: HarmonyGroup)
        fun onAddGroupBtnClicked()
    }

    fun bindData(groups: List<HarmonyGroup>)
    fun detachFromRecyclerView()

    fun showMessage(msg: String)
    fun showMessage(msgId: Int)

}