package dev.timatifey.harmony.screen.home.groups.grouplist

import dev.timatifey.harmony.common.mvp.MvpPresenter
import dev.timatifey.harmony.common.nav.AppScreenNavigator
import dev.timatifey.harmony.common.nav.BackPressDispatcher
import dev.timatifey.harmony.data.Status
import dev.timatifey.harmony.data.model.harmony.HarmonyGroup
import dev.timatifey.harmony.screen.RequireDrawerDispatcher
import dev.timatifey.harmony.screen.activity.DrawerDispatcher
import dev.timatifey.harmony.service.GroupService
import kotlinx.coroutines.*

class GroupListPresenter(
    private val groupService: GroupService,
    private val backPressDispatcher: BackPressDispatcher,
    private val appScreenNavigator: AppScreenNavigator,
): MvpPresenter<GroupListMvpView>, GroupListMvpView.Listener, RequireDrawerDispatcher {

    private lateinit var view: GroupListMvpView
    private lateinit var drawer: DrawerDispatcher

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private val groups = mutableListOf<HarmonyGroup>()

    override fun bindView(view: GroupListMvpView) {
        this.view = view
        initGroupList()
    }

    private fun initGroupList() {
        coroutineScope.launch {
            val groupsResource = groupService.getGroups()
            if (groupsResource.status is Status.Success) {
                groups.addAll(groupsResource.data!!)
                view.bindData(groups)
            } else {
                view.showMessage("GROUPS ERROR") //TODO("handle error groups")
            }
        }
    }

    override fun onStart() {
        view.registerListener(this)
        backPressDispatcher.registerListener(this)
    }

    override fun onStop() {
        view.unregisterListener(this)
        view.detachFromRecyclerView()
        backPressDispatcher.unregisterListener(this)
    }

    override fun onDestroy() {
        coroutineScope.coroutineContext.cancelChildren()
    }

    override fun onGroupClicked(group: HarmonyGroup) {
        view.showMessage("Clicked ${group.name}")
//        appScreenNavigator.toLobby(group.id)
    }

    override fun onMenuBtnClicked() {
        drawer.openDrawer()
    }

    override fun onGroupSwiped(group: HarmonyGroup) {
        groups.remove(group)
        coroutineScope.launch {
            groupService.deleteGroup(group)
        }
        view.bindData(groups)
    }

    override fun onAddGroupBtnClicked() {
        appScreenNavigator.toAddGroupFragment()
    }

    override fun onBackPressed(): Boolean {
        return false
    }

    override fun bindDrawerDispatcher(drawer: DrawerDispatcher) {
        this.drawer = drawer
    }
}