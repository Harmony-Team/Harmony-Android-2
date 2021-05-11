package dev.timatifey.harmony.screen.home.group.list

import dev.timatifey.harmony.R
import dev.timatifey.harmony.common.mvp.MvpPresenter
import dev.timatifey.harmony.common.nav.app.AppScreenNavigator
import dev.timatifey.harmony.common.nav.BackPressDispatcher
import dev.timatifey.harmony.data.Status
import dev.timatifey.harmony.data.model.harmony.HarmonyGroup
import dev.timatifey.harmony.screen.RequireMenuController
import dev.timatifey.harmony.screen.activity.MenuController
import dev.timatifey.harmony.service.GroupService
import kotlinx.coroutines.*

class GroupListPresenter(
    private val groupService: GroupService,
    private val backPressDispatcher: BackPressDispatcher,
    private val appScreenNavigator: AppScreenNavigator,
): MvpPresenter<GroupListMvpView>, GroupListMvpView.Listener, RequireMenuController {

    private lateinit var view: GroupListMvpView
    private lateinit var menu: MenuController

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
                groups.clear()
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
        appScreenNavigator.toLobby(group.id)
    }

    override fun onMenuBtnClicked() {
        menu.openMenu()
    }

    override fun onGroupSwiped(group: HarmonyGroup) {
        coroutineScope.launch {
            when(groupService.deleteGroup(group).status) {
                is Status.Success -> {
                    groups.remove(group)
                    view.showSnake(R.string.leaved_from_group)
                    view.bindData(groups)
                }
                is Status.Error -> {
                    view.showMessage("GROUPS ERROR")
                }
            }

        }
    }

    override fun onAddGroupBtnClicked() {
        appScreenNavigator.toAddGroupFragment()
    }

    override fun onBackPressed(): Boolean {
        return false
    }

    override fun bindMenuController(menuController: MenuController) {
        this.menu = menuController
    }
}