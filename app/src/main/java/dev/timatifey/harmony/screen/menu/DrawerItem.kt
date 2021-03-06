package dev.timatifey.harmony.screen.menu

import android.view.ViewGroup

abstract class DrawerItem<T : DrawerAdapter.ViewHolder> {

    var isChecked = false
        protected set

    abstract fun createViewHolder(parent: ViewGroup): T
    abstract fun bindViewHolder(holder: T)

    fun setChecked(isChecked: Boolean): DrawerItem<T> {
        this.isChecked = isChecked
        return this
    }

    open val isSelectable: Boolean
        get() = true
}