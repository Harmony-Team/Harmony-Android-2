package dev.timatifey.harmony.data.database

import androidx.room.*
import dev.timatifey.harmony.data.entities.TrackEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TracksDao {

    @Insert(entity = TrackEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTrack(track: TrackEntity)

    @Insert(entity = TrackEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTracks(tracks: List<TrackEntity>)

    @Query("SELECT * FROM ${TrackEntity.TABLE_NAME} ORDER by addedAt DESC")
    fun loadAllTracks(): Flow<List<TrackEntity>>

    @Query("SELECT * FROM ${TrackEntity.TABLE_NAME} WHERE id = :id")
    fun getTrackById(id: String): Flow<TrackEntity>

    @Update
    suspend fun updateTrack(track: TrackEntity)

    @Delete
    suspend fun deleteTrack(track: TrackEntity)

    @Query("DELETE FROM ${TrackEntity.TABLE_NAME}")
    suspend fun nukeTable()

}