package dev.timatifey.harmony.screen.menu

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatTextView
import dev.timatifey.harmony.R

class SimpleItem(private val title: String) : DrawerItem<SimpleItem.ViewHolder>() {

    private var selectedItemTextAppearance = 0
    private var selectedItemTextTint = 0
    private var normalItemTextAppearance = 0
    private var normalItemTextTint = 0

    class ViewHolder(itemView: View) : DrawerAdapter.ViewHolder(itemView) {
        val title: AppCompatTextView = itemView.findViewById(R.id.drawer_item_option__title)
    }

    override fun createViewHolder(parent: ViewGroup): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v: View = inflater.inflate(R.layout.drawer_item_option, parent, false)
        return ViewHolder(v)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun bindViewHolder(holder: ViewHolder) {
        holder.title.apply {
            text = title
            setTextColor(if (isChecked) selectedItemTextTint else normalItemTextTint)
            setTextAppearance(if (isChecked) selectedItemTextAppearance else normalItemTextAppearance)
        }
    }

    fun withSelectedTextAppearance(selectedItemTextAppearance: Int): SimpleItem {
        this.selectedItemTextAppearance = selectedItemTextAppearance
        return this
    }

    fun withSelectedTextTint(selectedItemTextTint: Int): SimpleItem {
        this.selectedItemTextTint = selectedItemTextTint
        return this
    }

    fun withTextAppearance(normalItemTextAppearance: Int): SimpleItem {
        this.normalItemTextAppearance = normalItemTextAppearance
        return this
    }

    fun withTextTint(normalItemTextTint: Int): SimpleItem {
        this.normalItemTextTint = normalItemTextTint
        return this
    }
}
