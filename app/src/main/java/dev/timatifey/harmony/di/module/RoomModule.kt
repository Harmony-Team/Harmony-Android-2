package dev.timatifey.harmony.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dev.timatifey.harmony.data.database.AppDatabase
import dev.timatifey.harmony.data.database.GroupsDao
import dev.timatifey.harmony.data.database.TracksDao
import javax.inject.Singleton

@Module
object RoomModule {
    private const val DATABASE_NAME = "harmony_database"

    @Singleton
    @Provides
    fun provideDatabase(application: Application): AppDatabase =
        Room.databaseBuilder(
            application.applicationContext,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()

    @Singleton
    @Provides
    fun provideGroupsDao(database: AppDatabase): GroupsDao = database.groupsDao()

    @Singleton
    @Provides
    fun provideTracksDao(database: AppDatabase): TracksDao = database.tracksDao()

}