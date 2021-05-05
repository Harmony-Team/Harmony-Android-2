package dev.timatifey.harmony.screen.home.lobby

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.timatifey.harmony.common.mvp.factory.MvpViewFactory
import dev.timatifey.harmony.data.model.harmony.AddUserBtn
import dev.timatifey.harmony.data.model.harmony.HarmonyLobbyItem
import dev.timatifey.harmony.screen.home.lobby.row.LobbyRowMvpView

class LobbyAdapter(
    private val listener: LobbyRowMvpView.Listener,
    private val mvpViewFactory: MvpViewFactory,
    private val context: Context
) : RecyclerView.Adapter<LobbyAdapter.LobbyViewHolder>(), LobbyRowMvpView.Listener {

    class LobbyViewHolder(val lobbyRowMvpView: LobbyRowMvpView) :
        RecyclerView.ViewHolder(lobbyRowMvpView.rootView)

    var items: MutableList<HarmonyLobbyItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LobbyViewHolder {
        val view = mvpViewFactory.createLobbyRowView(parent, this, context)
        return LobbyViewHolder(view)
    }

    override fun onBindViewHolder(holder: LobbyViewHolder, position: Int) {
        val item = items[position]
        holder.lobbyRowMvpView.bindData(item)
    }

    override fun getItemCount(): Int = items.size

    fun bindData(items: List<HarmonyLobbyItem>) {
        this.items.clear()
        this.items.add(AddUserBtn)
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onItemClicked(item: HarmonyLobbyItem) {
        listener.onItemClicked(item)
    }
}