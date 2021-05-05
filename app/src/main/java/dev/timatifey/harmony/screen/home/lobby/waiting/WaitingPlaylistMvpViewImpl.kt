package dev.timatifey.harmony.screen.home.lobby.waiting

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import dev.timatifey.harmony.R
import dev.timatifey.harmony.common.mvp.MvpViewBase
import dev.timatifey.harmony.common.mvp.MvpViewObservableBase

class WaitingPlaylistMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?,
) : MvpViewObservableBase<WaitingPlaylistMvpView.Listener>(), WaitingPlaylistMvpView {
    override var rootView: View = layoutInflater.inflate(R.layout.fragment_waiting, parent, false)
    private val btnNotReady: AppCompatButton = findViewById(R.id.fragment_waiting__not_ready_btn)

    init {
        btnNotReady.setOnClickListener { listeners.forEach { it.onNotReadyBtnClicked() } }
    }
}