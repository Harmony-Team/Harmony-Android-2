package dev.timatifey.harmony.di.module

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.FragmentManager
import dagger.Module
import dagger.Provides
import dev.timatifey.harmony.app.AppSettings
import dev.timatifey.harmony.di.scope.ActivityScope
import dev.timatifey.harmony.common.base.BaseActivity
import dev.timatifey.harmony.common.nav.BackPressDispatcher
import dev.timatifey.harmony.common.nav.app.AppScreenNavigator
import dev.timatifey.harmony.common.nav.lobby.LobbyFragmentNavigator
import dev.timatifey.harmony.di.qual.ActivityFragmentManager
import dev.timatifey.harmony.repo.lobby.LobbyProvider

@Module
object ActivityModule {

    @Provides
    @ActivityScope
    fun provideLayoutInflater(baseActivity: BaseActivity): LayoutInflater =
        LayoutInflater.from(baseActivity)

    @Provides
    @ActivityFragmentManager
    @ActivityScope
    fun provideBaseActivityFragmentManager(baseActivity: BaseActivity): FragmentManager =
        baseActivity.supportFragmentManager

    @Provides
    @ActivityScope
    fun provideContext(activity: Activity): Context = activity

    @Provides
    @ActivityScope
    fun provideAppScreenNavigator(
        appSettings: AppSettings,
        @ActivityFragmentManager fragmentManager: FragmentManager,
        savedInstanceState: Bundle?
    ): AppScreenNavigator =
        AppScreenNavigator(appSettings, fragmentManager, savedInstanceState)

    @Module
    interface Binds {
        @dagger.Binds
        fun backPressDispatcher(baseActivity: BaseActivity): BackPressDispatcher
    }
}
