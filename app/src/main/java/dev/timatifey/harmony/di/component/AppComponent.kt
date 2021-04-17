package dev.timatifey.harmony.di.component

import dagger.Component
import dev.timatifey.harmony.di.module.AppModule
import dev.timatifey.harmony.di.module.NetworkModule
import dev.timatifey.harmony.di.module.PresentationModule
import dev.timatifey.harmony.di.module.RoomModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RoomModule::class, NetworkModule::class])
interface AppComponent {

    fun newPresentationComponent(presentationModule: PresentationModule): PresentationComponent
}