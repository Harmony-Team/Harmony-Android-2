package dev.timatifey.harmony.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dev.timatifey.harmony.app.AppSettings
import dev.timatifey.harmony.app.Config.Companion.PREFERENCE_NAME
import dev.timatifey.harmony.repo.groups.GroupsRepo
import dev.timatifey.harmony.repo.groups.GroupsRepoImpl
import dev.timatifey.harmony.repo.tracks.TracksRepo
import dev.timatifey.harmony.repo.tracks.TracksRepoImpl
import dev.timatifey.harmony.repo.user.UserRepo
import dev.timatifey.harmony.repo.user.UserRepoImpl
import dev.timatifey.harmony.service.AuthHarmonyService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
object AppModule {

    @Provides
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun provideAppSettings(authHarmonyService: AuthHarmonyService): AppSettings =
        AppSettings(authHarmonyService)

    @Provides
    fun provideSharedPreferences(application: Application): SharedPreferences =
        application.getSharedPreferences(
            PREFERENCE_NAME,
            Context.MODE_PRIVATE
        )

    @Module
    interface Binds {
        @dagger.Binds
        fun userRepo(userRepoImpl: UserRepoImpl): UserRepo

        @dagger.Binds
        fun groupsRepo(groupsRepoImpl: GroupsRepoImpl): GroupsRepo

        @dagger.Binds
        fun tracksRepo(tracksRepoImpl: TracksRepoImpl): TracksRepo
    }
}
