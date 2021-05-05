package dev.timatifey.harmony.screen.home.groups.grouplist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.timatifey.harmony.R
import dev.timatifey.harmony.common.ItemSwipeManager
import dev.timatifey.harmony.common.mvp.MvpViewObservableBase
import dev.timatifey.harmony.common.mvp.factory.MvpViewFactory
import dev.timatifey.harmony.data.model.harmony.HarmonyGroup
import dev.timatifey.harmony.screen.home.groups.grouplist.row.GroupListRowMvpView

class GroupListMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?,
    viewFactory: MvpViewFactory,
) : MvpViewObservableBase<GroupListMvpView.Listener>(), GroupListMvpView,
    GroupListRowMvpView.Listener {

    override var rootView: View =
        layoutInflater.inflate(R.layout.fragment_group_list, parent, false)

    private val ibAddGroup: AppCompatImageButton =
        findViewById(R.id.fragment_groups__btn_create_new)
    private val ibMenu: AppCompatImageButton = findViewById(R.id.fragment_groups__ic_menu)
    private val rvGroupList: RecyclerView = findViewById(R.id.fragment_groups__recycler_view)
    private val adapter: GroupListAdapter = viewFactory.createGroupListAdapter(this, context)
    private val itemSwipeManager = ItemSwipeManager(context, adapter)

    init {
        rvGroupList.layoutManager = LinearLayoutManager(context)

        val itemDecoration = DividerItemDecoration(context, RecyclerView.VERTICAL)
        getDrawable(R.drawable.group_list__item_divider)?.let { itemDecoration.setDrawable(it) }
        rvGroupList.addItemDecoration(itemDecoration)

        rvGroupList.adapter = adapter

        itemSwipeManager.attachToRecyclerView(rvGroupList)

        ibMenu.setOnClickListener { listeners.forEach { it.onMenuBtnClicked() } }
        ibAddGroup.setOnClickListener { listeners.forEach { it.onAddGroupBtnClicked() } }
    }

    override fun bindData(groups: List<HarmonyGroup>) {
        adapter.bindData(groups)
    }

    override fun detachFromRecyclerView() {
        itemSwipeManager.detachFromRecyclerView()
    }

    override fun showMessage(msg: String) {
        showToast(msg)
    }

    override fun showMessage(msgId: Int) {
        showToast(msgId)
    }

    override fun onGroupClicked(group: HarmonyGroup) {
        listeners.forEach { it.onGroupClicked(group) }
    }

    override fun onGroupSwiped(group: HarmonyGroup) {
        listeners.forEach { it.onGroupSwiped(group) }
    }
}