package dev.timatifey.harmony.screen.home.lobby.tabs.tracks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
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
    private val scroll: ViewGroup = findViewById(R.id.fragment_music__waiting_scroll)

    private val tracksAdapter: TracksAdapter = viewFactory.createTracksAdapter(this, context)

    init {
        recyclerView.apply {
            adapter = tracksAdapter
            layoutManager = LinearLayoutManager(context)
            val itemDecoration = DividerItemDecoration(context, RecyclerView.VERTICAL)
            getDrawable(R.drawable.group_list__item_divider)?.let { itemDecoration.setDrawable(it) }
            addItemDecoration(itemDecoration)
        }
        btnReady.setOnClickListener { listeners.forEach { it.onReadyBtnClicked(tracksAdapter.getSelectedTracks()) } }
        btnNotReady.setOnClickListener { listeners.forEach { it.onNotReadyBtnClicked(tracksAdapter.getSelectedTracks()) } }
    }

    override fun showReadyBtn() {
        val animation = AnimationUtils.loadAnimation(context, R.anim.fade_in)
        btnReady.visibility = View.VISIBLE
        recyclerView.visibility = View.VISIBLE
        scroll.visibility = View.GONE
//        btnNotReady.visibility = View.GONE

//        btnReady.startAnimation(animation)
        recyclerView.startAnimation(animation)
    }

    override fun showNotReadyBtn() {
        val animation = AnimationUtils.loadAnimation(context, R.anim.fade_in)
        btnReady.visibility = View.GONE
        recyclerView.visibility = View.GONE
        scroll.visibility = View.VISIBLE
        btnNotReady.visibility = View.VISIBLE
        scroll.startAnimation(animation)
//        btnNotReady.startAnimation(animation)
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
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

    override fun stopLastPlayingTrack() {
        tracksAdapter.stopLastPlayingTrack()
    }

    override fun onTrackClicked(trackView: TracksRowMvpView, track: SpotifyLobbyTrack) {
        listeners.forEach { it.onTrackClicked(trackView, track) }
    }

    @SuppressWarnings("unused")
    override fun onCheckBtnClicked(trackView: TracksRowMvpView, track: SpotifyLobbyTrack) {
    }
}