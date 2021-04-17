package dev.timatifey.harmony.di.module

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import dev.timatifey.harmony.screen.common.nav.ScreenNavigator
import com.ncapdevi.fragnav.FragNavController
import dagger.Module
import dagger.Provides
import dev.timatifey.harmony.R
import dev.timatifey.harmony.di.scopes.Presentation

@Module
class PresentationModule(
    private val activity: AppCompatActivity,
    private val savedInstanceState: Bundle
) {
    @Provides
    @Presentation
    fun provideFragmentManager(): FragmentManager {
        return activity.supportFragmentManager
    }

    @Provides
    @Presentation
    fun provideLayoutInflater(): LayoutInflater {
        return LayoutInflater.from(activity)
    }

    @Provides
    @Presentation
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    @Presentation
    fun provideContext(activity: Activity): Context {
        return activity
    }

    @Provides
    @Presentation
    fun provideFragNavController(fragmentManager: FragmentManager): FragNavController {
        return FragNavController(fragmentManager, R.id.container)
    }

    @Provides
    @Presentation
    fun provideScreenNavigator(fragNavController: FragNavController): ScreenNavigator {
        return ScreenNavigator(fragNavController, savedInstanceState)
    }
}
