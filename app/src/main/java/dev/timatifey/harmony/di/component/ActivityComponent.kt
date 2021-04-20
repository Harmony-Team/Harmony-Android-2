package dev.timatifey.harmony.di.component

import android.os.Bundle
import dagger.BindsInstance
import dagger.Subcomponent
import dev.timatifey.harmony.di.module.ActivityModule
import dev.timatifey.harmony.di.scope.ActivityScope
import dev.timatifey.harmony.common.base.BaseActivity

@Subcomponent(modules = [
        ActivityModule::class,
        ActivityModule.Binds::class,
])
@ActivityScope
interface ActivityComponent {

    fun newPresentationComponent(): PresentationComponent

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun activity(baseActivity: BaseActivity): Builder

        @BindsInstance
        fun savedInstanceState(bundle: Bundle?): Builder

        fun build(): ActivityComponent
    }

    fun inject(baseActivity: BaseActivity)
}
