package dev.timatifey.harmony.service

import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.util.Log
import android.widget.Toast
import dev.timatifey.harmony.R
import dev.timatifey.harmony.api.spotify.SpotifyAPI
import dev.timatifey.harmony.data.Resource
import dev.timatifey.harmony.data.mappers.toSpotifyTrack
import dev.timatifey.harmony.data.model.harmony.Token
import dev.timatifey.harmony.data.model.spotify.SpotifyTrack
import dev.timatifey.harmony.repo.tracks.TracksRepo
import dev.timatifey.harmony.repo.user.UserRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class TracksService @Inject constructor(
    private val mediaPlayer: MediaPlayer,
    private val userRepo: UserRepo,
    private val tracksRepo: TracksRepo,
    private val spotifyApi: SpotifyAPI,
    private val ioDispatcher: CoroutineDispatcher,
) {

    suspend fun getTracks(): Resource<List<SpotifyTrack>> =
        withContext(ioDispatcher) {
            try {
                val cachedTracks = tracksRepo.getAllTracks().first()
//                Log.e("TracksService", cachedTracks.toString())
                if (cachedTracks.isNotEmpty()) {
                    return@withContext Resource.success(cachedTracks)
                }
//                Log.e("TracksService", "LOAD TRACKS")
                return@withContext fetchTracks()
            } catch (t: Throwable) {
                return@withContext Resource.error(msg = t.message)
            }
        }

    private suspend fun fetchTracks(): Resource<List<SpotifyTrack>> =
        withContext(ioDispatcher) {
            try {
                val spotifyTokens = userRepo.getSpotifyTokens().data
                    ?: return@withContext Resource.error(msgId = R.string.token_does_not_exist)
                val token = Token("Bearer ${spotifyTokens.accessToken.value}")
                val playlistsDto = spotifyApi.getCurrentUserPlaylists(token)
                if (playlistsDto.items == null) {
                    return@withContext Resource.error(msg = "Playlist items is null")
                }
                val tracks = mutableListOf<SpotifyTrack>()
                for (playlist in playlistsDto.items) {
                    val playlistTracks =
                        spotifyApi.getPlaylistTracks(token, playlist.id)
                    if (playlistTracks.items == null) continue
                    for (item in playlistTracks.items) {
                        val track = item.toSpotifyTrack()
                        tracks.add(track)
                        tracksRepo.addTrack(track)
                    }
                }
                return@withContext Resource.success(tracks)
            } catch (t: Throwable) {
                return@withContext Resource.error(msg = t.message)
            }
        }

    fun playAudio(track: SpotifyTrack, completionListener: MediaPlayer.OnCompletionListener) {
        stopPlayAudio()
        mediaPlayer.apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build()
            )
            setDataSource(track.previewUrl)
            setOnPreparedListener {
                start()
            }
            setOnCompletionListener(completionListener)
            prepareAsync()
        }
    }

    fun stopPlayAudio() {
        mediaPlayer.apply {
            stop()
            reset()
        }
    }
}