package dev.timatifey.harmony.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import dev.timatifey.harmony.api.harmony.HarmonyAPI
import dev.timatifey.harmony.repo.user.UserRepo
import dev.timatifey.harmony.repo.user.UserRepoImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
object AppModule {

    @Provides
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Module
    interface Binds {

        @dagger.Binds
        fun userRepo(userRepoImpl: UserRepoImpl): UserRepo
    }
}
