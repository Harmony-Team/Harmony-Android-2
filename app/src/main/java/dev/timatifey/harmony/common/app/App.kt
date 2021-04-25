package dev.timatifey.harmony.common.app

import android.app.Application
import android.util.Log
import dev.timatifey.harmony.di.component.AppComponent
import dev.timatifey.harmony.di.component.DaggerAppComponent


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