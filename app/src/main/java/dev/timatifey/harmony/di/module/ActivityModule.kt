package dev.timatifey.harmony.di.module

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.FragmentManager
import com.ncapdevi.fragnav.FragNavController
import dagger.Module
import dagger.Provides
import dev.timatifey.harmony.R
import dev.timatifey.harmony.common.app.AppSettings
import dev.timatifey.harmony.di.scope.ActivityScope
import dev.timatifey.harmony.common.base.BaseActivity
import dev.timatifey.harmony.common.nav.BackPressDispatcher
import dev.timatifey.harmony.common.nav.app.AppScreenNavigator
import javax.inject.Named

@Module
object ActivityModule {

    @Provides
    @ActivityScope
    fun provideLayoutInflater(baseActivity: BaseActivity): LayoutInflater =
        LayoutInflater.from(baseActivity)

    @Provides
    @Named("ActivityFragmentManager")
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
        @Named("ActivityFragmentManager") fragmentManager: FragmentManager,
        savedInstanceState: Bundle?
    ): AppScreenNavigator =
        AppScreenNavigator(appSettings, fragmentManager, savedInstanceState)

    @Module
    interface Binds {
        @dagger.Binds
        fun bindBackPressDispatcher(baseActivity: BaseActivity): BackPressDispatcher
    }
}
