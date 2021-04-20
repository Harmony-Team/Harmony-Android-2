package dev.timatifey.harmony.di.component

import dagger.Subcomponent
import dev.timatifey.harmony.di.scope.PresentationScope
import dev.timatifey.harmony.screen.auth.signin.SignInFragment
import dev.timatifey.harmony.common.MainActivity
import dev.timatifey.harmony.common.base.BaseActivity
import dev.timatifey.harmony.screen.splash.SplashFragment

@PresentationScope
@Subcomponent(modules = [])
interface PresentationComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(baseActivity: BaseActivity)

    fun inject(signInFragment: SignInFragment)

    fun inject(splashFragment: SplashFragment)
}
