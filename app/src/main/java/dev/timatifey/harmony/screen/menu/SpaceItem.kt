package dev.timatifey.harmony.screen.menu

import android.view.View
import android.view.ViewGroup

class SpaceItem(private val spaceDp: Int) : DrawerItem<SpaceItem.ViewHolder>() {

    override val isSelectable: Boolean
        get() = false

    inner class ViewHolder(itemView: View) : DrawerAdapter.ViewHolder(itemView)

    override fun createViewHolder(parent: ViewGroup): ViewHolder {
        val c = parent.context
        val view = View(c)
        val height = (c.resources.displayMetrics.density * spaceDp).toInt()
        view.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            height
        )
        return ViewHolder(view)
    }

    @SuppressWarnings("unused")
    override fun bindViewHolder(holder: ViewHolder) {
    }
}
