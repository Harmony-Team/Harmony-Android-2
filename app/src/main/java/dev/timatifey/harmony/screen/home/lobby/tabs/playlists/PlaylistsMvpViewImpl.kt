package dev.timatifey.harmony.screen.home.lobby.tabs.playlists

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import dev.timatifey.harmony.R
import dev.timatifey.harmony.common.mvp.MvpViewObservableBase

class PlaylistsMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?,
) : MvpViewObservableBase<PlaylistsMvpView.Listener>(), PlaylistsMvpView {

    override var rootView: View = layoutInflater.inflate(R.layout.fragment_playlists, parent, false)
    private val btnCreate: AppCompatButton = findViewById(R.id.fragment_playlists__create_btn)

    init {
        btnCreate.setOnClickListener { listeners.forEach { it.onCreateBtnClicked() } }
    }

    override fun showCreateBtn() {
        btnCreate.visibility = View.VISIBLE
    }

    override fun hideCreateBtn() {
        btnCreate.visibility = View.INVISIBLE
    }

}