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
import dev.timatifey.harmony.di.scope.ActivityScope
import dev.timatifey.harmony.common.base.BaseActivity
import dev.timatifey.harmony.common.nav.BackPressDispatcher
import dev.timatifey.harmony.common.nav.app.AppScreenNavigator

@Module
object ActivityModule {

    @Provides
    @ActivityScope
    fun provideLayoutInflater(baseActivity: BaseActivity): LayoutInflater =
        LayoutInflater.from(baseActivity)

    @Provides
    @ActivityScope
    fun provideFragmentManager(baseActivity: BaseActivity): FragmentManager =
        baseActivity.supportFragmentManager

    @Provides
    @ActivityScope
    fun provideContext(activity: Activity): Context = activity

    @Provides
    @ActivityScope
    fun provideFragNavController(fragmentManager: FragmentManager): FragNavController =
        FragNavController(fragmentManager, R.id.container)

    @Provides
    @ActivityScope
    fun provideAppScreenNavigator(
        fragNavController: FragNavController,
        savedInstanceState: Bundle?
    ): AppScreenNavigator =
        AppScreenNavigator(fragNavController, savedInstanceState)

    @Module
    interface Binds {
        @dagger.Binds
        fun bindBackPressDispatcher(baseActivity: BaseActivity): BackPressDispatcher
    }
}
