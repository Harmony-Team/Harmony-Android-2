package dev.timatifey.harmony.screen.home.lobby.tabs.tracks.row

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.transition.Fade
import androidx.transition.Transition
import androidx.transition.TransitionListenerAdapter
import androidx.transition.TransitionManager
import androidx.vectordrawable.graphics.drawable.AnimationUtilsCompat
import com.bumptech.glide.RequestManager
import dev.timatifey.harmony.R
import dev.timatifey.harmony.common.mvp.MvpViewBase
import dev.timatifey.harmony.data.model.spotify.SpotifyLobbyTrack


class TracksRowMvpViewImpl(
    layoutInflater: LayoutInflater,
    private val parent: ViewGroup,
    private val listener: TracksRowMvpView.Listener,
    private val glide: RequestManager,
) : MvpViewBase(), TracksRowMvpView {

    override var rootView: View = layoutInflater.inflate(R.layout.track_list__item, parent, false)
    private val ivImage: AppCompatImageView = findViewById(R.id.track_list__item__image)
    private val ivPlaying: AppCompatImageView = findViewById(R.id.track_list__item__image_playing)
    private val ivPlayingCard: CardView = findViewById(R.id.track_list__item__image_playing_card)
    private val tvName: AppCompatTextView = findViewById(R.id.track_list__item__track_name)
    private val tvArtist: AppCompatTextView =
        findViewById(R.id.track_list__item__track_artist_name)
    private val ibCheck: AppCompatImageButton = findViewById(R.id.track_list__item__check_btn)

    private lateinit var track: SpotifyLobbyTrack

    init {
        rootView.setOnClickListener {
            listener.onTrackClicked(this, track)
        }
        ibCheck.setOnClickListener {
            listener.onCheckBtnClicked(this, track)
        }
        glide.load(R.raw.gif_track_is_playing_cropped).into(ivPlaying)
    }

    override fun bindData(track: SpotifyLobbyTrack) {
        this.track = track
        tvName.text = track.name
        tvArtist.text = track.artistName
        glide.load(track.albumImage).into(ivImage)
        if (track.isPlaying) {
            showPlayingImage()
        } else {
            hidePlayingImage()
        }
        ibCheck.setBackgroundResource(
            if (track.isSelected) {
                R.drawable.ic_track_checked
            } else {
                R.drawable.ic_track_unchecked
            }
        )
    }

    override fun showPlayingImage() {
        val animation = AnimationUtils.loadAnimation(context, R.anim.fade_in)
        ivPlayingCard.visibility = View.VISIBLE
        ivPlayingCard.startAnimation(animation)
    }

    override fun hidePlayingImage() {
        ivPlayingCard.visibility = View.INVISIBLE
    }

    override fun isSelectedTrack(isSelected: Boolean) {
        val animation = AnimationUtils.loadAnimation(context, R.anim.fade_in)
        ibCheck.setBackgroundResource(
            if (isSelected) {
                R.drawable.ic_track_checked
            } else {
                R.drawable.ic_track_unchecked
            }
        )
        ibCheck.startAnimation(animation)
    }
}