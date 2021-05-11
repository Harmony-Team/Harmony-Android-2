package dev.timatifey.harmony.repo.tracks

import dev.timatifey.harmony.data.database.TracksDao
import dev.timatifey.harmony.data.mappers.toEntity
import dev.timatifey.harmony.data.mappers.toModel
import dev.timatifey.harmony.data.model.spotify.SpotifyTrack
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TracksRepoImpl @Inject constructor(
    private val tracksDao: TracksDao,
) : TracksRepo {

    override fun getAllTracks(): Flow<List<SpotifyTrack>> =
        tracksDao.loadAllTracks()
            .map {
                it.map { trackEntity ->
                    trackEntity.toModel()
                }
            }

    override fun getTrackById(id: String): Flow<SpotifyTrack> =
        tracksDao.getTrackById(id)
            .map { it.toModel() }

    override suspend fun addTrack(track: SpotifyTrack) {
        tracksDao.addTrack(track.toEntity())
    }

    override suspend fun addTracks(tracks: List<SpotifyTrack>) {
        tracksDao.addTracks(tracks.map { it.toEntity() })
    }

    override suspend fun deleteTrack(track: SpotifyTrack) {
        tracksDao.deleteTrack(track.toEntity())
    }

    override suspend fun clearAll() {
        tracksDao.nukeTable()
    }
}