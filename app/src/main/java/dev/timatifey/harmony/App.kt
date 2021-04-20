package dev.timatifey.harmony

import android.app.Application
import dev.timatifey.harmony.di.component.AppComponent
import dev.timatifey.harmony.di.component.DaggerAppComponent
import dev.timatifey.harmony.di.module.AppModule


class App: Application() {

    lateinit var appComponent: AppComponent private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .application(this)
            .build()
    }
}