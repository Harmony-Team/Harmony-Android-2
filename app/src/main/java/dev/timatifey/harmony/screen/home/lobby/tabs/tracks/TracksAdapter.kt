package dev.timatifey.harmony.screen.home.lobby.tabs.tracks

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.timatifey.harmony.common.mvp.factory.MvpViewFactory
import dev.timatifey.harmony.data.model.spotify.SpotifyLobbyTrack
import dev.timatifey.harmony.screen.home.lobby.tabs.tracks.row.TracksRowMvpView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class TracksAdapter(
    private val listener: TracksRowMvpView.Listener,
    private val mvpViewFactory: MvpViewFactory,
    private val context: Context,

    ) : RecyclerView.Adapter<TracksAdapter.TrackViewHolder>(), TracksRowMvpView.Listener {

    class TrackViewHolder(val trackRowMvpView: TracksRowMvpView) :
        RecyclerView.ViewHolder(trackRowMvpView.rootView)

    var tracks: MutableList<SpotifyLobbyTrack> = ArrayList()

    private var lastPlayingTrackIndex: Int? = null

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

    override fun onTrackClicked(trackView: TracksRowMvpView, track: SpotifyLobbyTrack) {
        if (track.isPlaying) {
            // Найти, выключить его и уведомить адаптер
            tracks.forEachIndexed { index, spotifyLobbyTrack ->
                if (spotifyLobbyTrack.id == track.id) {
                    spotifyLobbyTrack.isPlaying = false
                    notifyItemChanged(index)
                }
            }
        } else {
            // Найти, включить его, выключить предыдущий если был, уведомить адаптер об каждом
            tracks.forEachIndexed { index, spotifyLobbyTrack ->
                if (spotifyLobbyTrack.id == track.id) {
                    spotifyLobbyTrack.isPlaying = true
                    lastPlayingTrackIndex = index
                    notifyItemChanged(index)
                } else {
                    if (spotifyLobbyTrack.isPlaying) {
                        spotifyLobbyTrack.isPlaying = false
                        notifyItemChanged(index)
                    }
                }
            }
        }
        listener.onTrackClicked(trackView, track)
    }

    override fun onCheckBtnClicked(trackView: TracksRowMvpView, track: SpotifyLobbyTrack) {
        track.isSelected = track.isSelected.not()
        trackView.isSelectedTrack(track.isSelected)
    }

    fun bindData(trackList: List<SpotifyLobbyTrack>) {
        tracks.clear()
        tracks.addAll(trackList)
        notifyDataSetChanged()
    }

    fun getSelectedTracks(): List<SpotifyLobbyTrack> =
        tracks.filter { it.isSelected }

    fun stopLastPlayingTrack() {
        val index: Int = lastPlayingTrackIndex ?: return
        tracks[index].isPlaying = false
        notifyItemChanged(index)
    }
}