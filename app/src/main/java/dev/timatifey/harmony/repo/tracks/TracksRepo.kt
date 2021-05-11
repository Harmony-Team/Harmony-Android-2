package dev.timatifey.harmony.repo.tracks

import dev.timatifey.harmony.data.model.spotify.SpotifyTrack
import kotlinx.coroutines.flow.Flow

interface TracksRepo {
    fun getAllTracks(): Flow<List<SpotifyTrack>>
    fun getTrackById(id: String): Flow<SpotifyTrack>
    suspend fun addTrack(track: SpotifyTrack)
    suspend fun addTracks(tracks: List<SpotifyTrack>)
    suspend fun deleteTrack(track: SpotifyTrack)
    suspend fun clearAll()
}