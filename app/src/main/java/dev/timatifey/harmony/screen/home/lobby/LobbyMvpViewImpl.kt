package dev.timatifey.harmony.screen.home.lobby

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.FragmentContainer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.timatifey.harmony.R
import dev.timatifey.harmony.common.mvp.MvpViewObservableBase
import dev.timatifey.harmony.common.mvp.factory.MvpViewFactory
import dev.timatifey.harmony.data.model.harmony.HarmonyLobbyItem
import dev.timatifey.harmony.screen.home.lobby.row.LobbyRowMvpView

class LobbyMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?,
    viewFactory: MvpViewFactory,
) : MvpViewObservableBase<LobbyMvpView.Listener>(), LobbyMvpView,
    LobbyRowMvpView.Listener {

    override var rootView: View =
        layoutInflater.inflate(R.layout.fragment_lobby, parent, false)
    private val adapter: LobbyAdapter = viewFactory.createLobbyAdapter(this, context)
    private val ibBackArrow: AppCompatImageButton = findViewById(R.id.fragment_lobby__ic_back)
    private val ibSettings: AppCompatImageButton = findViewById(R.id.fragment_lobby__ic_settings)
    private val rvUsers: RecyclerView = findViewById(R.id.fragment_lobby__recycler_view)
    private val loading: ProgressBar = findViewById(R.id.fragment_lobby__loading)
    private val fragmentContainer: FrameLayout = findViewById(R.id.fragment_lobby__container)

    init {
        rvUsers.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        rvUsers.adapter = adapter
        ibBackArrow.setOnClickListener { listeners.forEach { it.onBackBtnClicked() } }
        ibSettings.setOnClickListener { listeners.forEach { it.onSettingsBtnClicked() } }
    }

    override fun bindData(users: List<HarmonyLobbyItem>) {
        adapter.bindData(users)
    }

    override fun isVisibleLoading(isVisible: Boolean) {
        if (isVisible) {
            loading.visibility = View.VISIBLE
        } else {
            loading.visibility = View.GONE
        }
    }

    override fun disableAll() {
        rvUsers.isEnabled = false
        ibBackArrow.isEnabled = false
        ibSettings.isEnabled = false
        fragmentContainer.isEnabled = false
    }

    override fun enableAll() {
        rvUsers.isEnabled = true
        ibBackArrow.isEnabled = true
        ibSettings.isEnabled = true
        fragmentContainer.isEnabled = true
    }

    override fun showMessage(msg: String) {
        showToast(msg)
    }

    override fun showMessage(msgId: Int) {
        showToast(msgId)
    }

    override fun onItemClicked(item: HarmonyLobbyItem) {
        listeners.forEach { it.onItemClicked(item) }
    }
}