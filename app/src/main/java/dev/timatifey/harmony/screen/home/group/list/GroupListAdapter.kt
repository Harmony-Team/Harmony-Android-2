package dev.timatifey.harmony.screen.home.group.list

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.timatifey.harmony.common.ItemSwipeManager
import dev.timatifey.harmony.common.mvp.factory.MvpViewFactory
import dev.timatifey.harmony.data.model.harmony.HarmonyGroup
import dev.timatifey.harmony.screen.home.group.list.row.GroupListRowMvpView

class GroupListAdapter(
    private val listener: GroupListRowMvpView.Listener,
    private val mvpViewFactory: MvpViewFactory,
    private val context: Context
) : RecyclerView.Adapter<GroupListAdapter.GroupViewHolder>(), GroupListRowMvpView.Listener,
    ItemSwipeManager.SwipeListener {

    class GroupViewHolder(val groupListRowMvpView: GroupListRowMvpView) :
        RecyclerView.ViewHolder(groupListRowMvpView.rootView)

    var groups: MutableList<HarmonyGroup> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        val view = mvpViewFactory.createGroupListRowView(parent, this, context)
        return GroupViewHolder(view)
    }

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        val currentGroup = groups[position]
        holder.groupListRowMvpView.bindData(currentGroup)
    }

    override fun getItemCount(): Int = groups.size

    override fun onGroupClicked(group: HarmonyGroup) {
        listener.onGroupClicked(group)
    }

    override fun onGroupSwiped(group: HarmonyGroup) {
        listener.onGroupSwiped(group)
        notifyDataSetChanged()
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder) {
        val position = viewHolder.absoluteAdapterPosition
        if (position != RecyclerView.NO_POSITION) {
            onGroupSwiped(groups[position])
        }
    }

    fun bindData(groupList: List<HarmonyGroup>) {
        groups.clear()
        groups.addAll(groupList)
        notifyDataSetChanged()
    }
}