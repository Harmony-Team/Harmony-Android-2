package dev.timatifey.harmony.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = TrackEntity.TABLE_NAME)
data class TrackEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val albumImage: String,
    val artistName: String,
    val durationMs: Long,
    val addedAt: String,
    val previewUrl: String,
) {
    companion object {
        const val TABLE_NAME = "tracks_table"
    }
}
