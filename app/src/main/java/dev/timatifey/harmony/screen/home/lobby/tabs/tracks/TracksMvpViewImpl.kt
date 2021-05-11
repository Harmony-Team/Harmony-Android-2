package dev.timatifey.harmony.screen.home.lobby.tabs.tracks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dev.timatifey.harmony.R
import dev.timatifey.harmony.common.mvp.MvpViewObservableBase
import dev.timatifey.harmony.common.mvp.factory.MvpViewFactory
import dev.timatifey.harmony.data.model.spotify.SpotifyLobbyTrack
import dev.timatifey.harmony.screen.home.lobby.tabs.tracks.row.TracksRowMvpView

class TracksMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?,
    viewFactory: MvpViewFactory,
) : MvpViewObservableBase<TracksMvpView.Listener>(), TracksMvpView, TracksRowMvpView.Listener {

    override var rootView: View = layoutInflater.inflate(R.layout.fragment_music, parent, false)
    private val btnReady: AppCompatButton = findViewById(R.id.fragment_music__ready_btn)
    private val btnNotReady: AppCompatButton = findViewById(R.id.fragment_music__not_ready_btn)
    private val recyclerView: RecyclerView = findViewById(R.id.fragment_music__recycler_view)
    private val progressBar: ProgressBar = findViewById(R.id.fragment_music__loading)
    private val waitingContent: View = findViewById(R.id.fragment_music__waiting_content)

    private val tracksAdapter: TracksAdapter = viewFactory.createTracksAdapter(this, context)

    init {
        recyclerView.apply {
            adapter = tracksAdapter
            layoutManager = LinearLayoutManager(context)
            val itemDecoration = DividerItemDecoration(context, RecyclerView.VERTICAL)
            getDrawable(R.drawable.group_list__item_divider)?.let { itemDecoration.setDrawable(it) }
            addItemDecoration(itemDecoration)
        }
        btnReady.setOnClickListener { listeners.forEach { it.onReadyBtnClicked() } }
        btnNotReady.setOnClickListener { listeners.forEach { it.onNotReadyBtnClicked() } }
    }

    override fun showReadyBtn() {
        btnReady.visibility = View.VISIBLE
        btnNotReady.visibility = View.INVISIBLE
        waitingContent.visibility = View.INVISIBLE
        recyclerView.visibility = View.VISIBLE
    }

    override fun showNotReadyBtn() {
        btnReady.visibility = View.INVISIBLE
        btnNotReady.visibility = View.VISIBLE
        waitingContent.visibility = View.VISIBLE
        recyclerView.visibility = View.INVISIBLE
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun showSnackbar(msg: String) {
        Snackbar.make(rootView, msg, Snackbar.LENGTH_SHORT).show()
    }

    override fun showSnackbar(msgId: Int) {
        Snackbar.make(rootView, getString(msgId), Snackbar.LENGTH_SHORT).show()
    }

    override fun bindData(tracks: List<SpotifyLobbyTrack>) {
        tracksAdapter.bindData(tracks)
    }

    override fun onTrackClicked(track: SpotifyLobbyTrack) {
        listeners.forEach { it.onTrackClicked(track) }
    }

    override fun onCheckBtnClicked(track: SpotifyLobbyTrack) {
        listeners.forEach { it.onTrackCheckBtnClicked(track) }
    }

}