package dev.timatifey.harmony.di.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dev.timatifey.harmony.data.database.AppDatabase
import dev.timatifey.harmony.data.database.GroupsDao
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()

    @Singleton
    @Provides
    fun provideGroupsDao(database: AppDatabase): GroupsDao = database.groupsDao()

    companion object {
        const val DATABASE_NAME = "harmony_database"
    }

}