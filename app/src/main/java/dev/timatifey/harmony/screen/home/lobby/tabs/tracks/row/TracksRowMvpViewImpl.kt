package dev.timatifey.harmony.screen.home.lobby.tabs.tracks.row

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.bumptech.glide.RequestManager
import dev.timatifey.harmony.R
import dev.timatifey.harmony.common.mvp.MvpViewBase
import dev.timatifey.harmony.data.model.spotify.SpotifyLobbyTrack

class TracksRowMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?,
    private val listener: TracksRowMvpView.Listener,
    private val glide: RequestManager,
) : MvpViewBase(), TracksRowMvpView {

    override var rootView: View = layoutInflater.inflate(R.layout.track_list__item, parent, false)
    private val ivImage: AppCompatImageView = findViewById(R.id.track_list__item__image)
    private val ivPlaying: AppCompatImageView = findViewById(R.id.track_list__item__image_playing)
    private val tvName: AppCompatTextView = findViewById(R.id.track_list__item__track_name)
    private val tvArtist: AppCompatTextView =
        findViewById(R.id.track_list__item__track_artist_name)
    private val ibCheck: AppCompatImageButton = findViewById(R.id.track_list__item__check_btn)

    private lateinit var track: SpotifyLobbyTrack

    init {
        rootView.setOnClickListener { listener.onTrackClicked(track) }
        ibCheck.setOnClickListener { listener.onCheckBtnClicked(track) }
    }

    override fun bindData(track: SpotifyLobbyTrack) {
        this.track = track
        tvName.text = track.name
        tvArtist.text = track.artistName
        ibCheck.setBackgroundResource(
            if (track.isSelected) {
                R.drawable.ic_track_checked
            } else {
                R.drawable.ic_track_unchecked
            }
        )
        glide.load(track.albumImage).into(ivImage)
        if (track.isPlaying) {
            showPlayingImage()
        } else {
            hidePlayingImage()
        }
    }

    override fun showPlayingImage() {
        ivPlaying.visibility = View.VISIBLE
    }

    override fun hidePlayingImage() {
        ivPlaying.visibility = View.INVISIBLE
    }
}