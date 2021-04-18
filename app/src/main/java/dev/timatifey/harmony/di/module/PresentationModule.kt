package dev.timatifey.harmony.di.module

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import dev.timatifey.harmony.screen.common.nav.app.AppScreenNavigatorImpl
import com.ncapdevi.fragnav.FragNavController
import dagger.Module
import dagger.Provides
import dev.timatifey.harmony.R
import dev.timatifey.harmony.di.scopes.Presentation
import dev.timatifey.harmony.screen.common.nav.app.AppScreenNavigator

@Module
class PresentationModule(
    private val activity: AppCompatActivity,
    private val savedInstanceState: Bundle
) {
    @Provides
    @Presentation
    fun provideFragmentManager(): FragmentManager = activity.supportFragmentManager

    @Provides
    @Presentation
    fun provideLayoutInflater(): LayoutInflater = LayoutInflater.from(activity)

    @Provides
    @Presentation
    fun provideActivity(): Activity = activity

    @Provides
    @Presentation
    fun provideContext(activity: Activity): Context = activity

    @Provides
    @Presentation
    fun provideFragNavController(fragmentManager: FragmentManager): FragNavController =
        FragNavController(fragmentManager, R.id.container)

    @Provides
    @Presentation
    fun provideAppScreenNavigator(fragNavController: FragNavController): AppScreenNavigator =
        AppScreenNavigatorImpl(fragNavController, savedInstanceState)
}
