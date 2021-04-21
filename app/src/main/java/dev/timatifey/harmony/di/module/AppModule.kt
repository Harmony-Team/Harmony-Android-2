package dev.timatifey.harmony.di.module

import dagger.Module
import dagger.Provides
import dev.timatifey.harmony.common.app.AppSettings
import dev.timatifey.harmony.repo.user.UserRepo
import dev.timatifey.harmony.repo.user.UserRepoImpl
import dev.timatifey.harmony.screen.activity.MainMvpView
import dev.timatifey.harmony.screen.activity.MainMvpViewImpl
import dev.timatifey.harmony.service.AuthService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
object AppModule {

    @Provides
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun provideAppSettings(authService: AuthService): AppSettings =
        AppSettings(authService)

    @Module
    interface Binds {
        @dagger.Binds
        fun userRepo(userRepoImpl: UserRepoImpl): UserRepo
    }
}
