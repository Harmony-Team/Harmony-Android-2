package dev.timatifey.harmony.screen.home.lobby.tabs.music

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import dev.timatifey.harmony.R
import dev.timatifey.harmony.common.mvp.MvpViewObservableBase

class MusicMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?,
) : MvpViewObservableBase<MusicMvpView.Listener>(), MusicMvpView {

    override var rootView: View = layoutInflater.inflate(R.layout.fragment_music, parent, false)
    private val btnReady: AppCompatButton = findViewById(R.id.fragment_music__ready_btn)
    private val btnNotReady: AppCompatButton = findViewById(R.id.fragment_music__not_ready_btn)
    private val waitingContent: View = findViewById(R.id.fragment_music__waiting_content)
    
    init {
        btnReady.setOnClickListener { listeners.forEach { it.onReadyBtnClicked() } }
        btnNotReady.setOnClickListener { listeners.forEach { it.onNotReadyBtnClicked() } }
    }

    override fun showReadyBtn() {
        btnReady.visibility = View.VISIBLE
        btnNotReady.visibility = View.INVISIBLE
        waitingContent.visibility = View.INVISIBLE
    }

    override fun showNotReadyBtn() {
        btnReady.visibility = View.INVISIBLE
        btnNotReady.visibility = View.VISIBLE
        waitingContent.visibility = View.VISIBLE
    }

}