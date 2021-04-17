package dev.timatifey.harmony.di.component

import dagger.Subcomponent
import dev.timatifey.harmony.di.module.PresentationModule
import dev.timatifey.harmony.di.scopes.Presentation
import dev.timatifey.harmony.screen.common.MainActivity
import dev.timatifey.harmony.screen.common.base.BaseActivity

@Presentation
@Subcomponent(modules = [PresentationModule::class])
interface PresentationComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(baseActivity: BaseActivity)
}
