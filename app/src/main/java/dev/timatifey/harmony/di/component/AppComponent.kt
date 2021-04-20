package dev.timatifey.harmony.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dev.timatifey.harmony.di.module.AppModule
import dev.timatifey.harmony.di.module.NetworkModule
import dev.timatifey.harmony.di.module.RoomModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    AppModule.Binds::class,
    RoomModule::class,
    NetworkModule::class
])
interface AppComponent {

    fun newActivityComponentBuilder(): ActivityComponent.Builder

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}