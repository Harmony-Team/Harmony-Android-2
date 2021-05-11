package dev.timatifey.harmony.screen.home.lobby.tabs.tracks

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.timatifey.harmony.common.mvp.factory.MvpViewFactory
import dev.timatifey.harmony.data.model.spotify.SpotifyLobbyTrack
import dev.timatifey.harmony.screen.home.lobby.tabs.tracks.row.TracksRowMvpView

class TracksAdapter(
    private val listener: TracksRowMvpView.Listener,
    private val mvpViewFactory: MvpViewFactory,
    private val context: Context
) : RecyclerView.Adapter<TracksAdapter.TrackViewHolder>(), TracksRowMvpView.Listener {

    class TrackViewHolder(val trackRowMvpView: TracksRowMvpView) :
        RecyclerView.ViewHolder(trackRowMvpView.rootView)

    var tracks: MutableList<SpotifyLobbyTrack> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val view = mvpViewFactory.createTrackRowMvpView(parent, this, context)
        return TrackViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        val track = tracks[position]
        holder.trackRowMvpView.bindData(track)
    }

    override fun getItemCount(): Int {
        return tracks.size
    }

    override fun onTrackClicked(track: SpotifyLobbyTrack) {
        listener.onTrackClicked(track)
        notifyDataChanged()
    }

    override fun onCheckBtnClicked(track: SpotifyLobbyTrack) {
        listener.onCheckBtnClicked(track)
        notifyDataChanged()
    }

    private fun notifyDataChanged() {
        notifyDataSetChanged()
    }

    fun bindData(trackList: List<SpotifyLobbyTrack>) {
        tracks.clear()
        tracks.addAll(trackList)
        notifyDataSetChanged()
    }
}