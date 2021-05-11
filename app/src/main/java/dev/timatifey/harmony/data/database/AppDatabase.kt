package dev.timatifey.harmony.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.timatifey.harmony.data.entities.GroupEntity
import dev.timatifey.harmony.data.entities.TrackEntity

@Database(
    entities = [GroupEntity::class, TrackEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun groupsDao(): GroupsDao

    abstract fun tracksDao(): TracksDao

}