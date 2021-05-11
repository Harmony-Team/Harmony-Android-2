package dev.timatifey.harmony.screen.menu

import android.util.SparseArray
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class DrawerAdapter(private val items: List<DrawerItem<ViewHolder>>) :
    RecyclerView.Adapter<DrawerAdapter.ViewHolder>() {

    private val viewTypes: MutableMap<Class<DrawerItem<ViewHolder>>, Int> =
        mutableMapOf()
    private val holderFactories: SparseArray<DrawerItem<ViewHolder>> = SparseArray()
    private var listener: OnItemSelectedListener? = null

    init {
        processViewTypes()
    }

    abstract class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var adapter: DrawerAdapter? = null

        init {
            itemView.setOnClickListener {
                adapter!!.setSelected(bindingAdapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder: ViewHolder = holderFactories[viewType].createViewHolder(parent)
        holder.adapter = this
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items[position].bindViewHolder(holder)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return viewTypes[items[position].javaClass]!!
    }

    private fun processViewTypes() {
        var type = 0
        for (item in items) {
            if (!viewTypes.containsKey(item.javaClass)) {
                viewTypes[item.javaClass] = type
                holderFactories.put(type, item)
                type++
            }
        }
    }

    fun setSelected(position: Int) {
        val newChecked = items[position]
        if (!newChecked.isSelectable) {
            return
        }
        for ((i,item) in items.withIndex()) {
            if (item.isChecked) {
                item.setChecked(false)
                notifyItemChanged(i)
                break
            }
        }
        newChecked.setChecked(true)
        notifyItemChanged(position)
        if (listener != null) {
            listener!!.onItemSelected(position)
        }
    }

    fun setListener(listener: OnItemSelectedListener?) {
        this.listener = listener
    }

    interface OnItemSelectedListener {
        fun onItemSelected(position: Int)
    }
}