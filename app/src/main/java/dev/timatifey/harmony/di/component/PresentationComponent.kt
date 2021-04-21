package dev.timatifey.harmony.di.component

import dagger.Subcomponent
import dev.timatifey.harmony.screen.auth.signin.SignInFragment
import dev.timatifey.harmony.screen.activity.MainActivity
import dev.timatifey.harmony.common.base.BaseActivity
import dev.timatifey.harmony.di.scope.PresentationScope
import dev.timatifey.harmony.screen.auth.recovery.RecoveryFragment
import dev.timatifey.harmony.screen.auth.signup.SignUpFragment
import dev.timatifey.harmony.screen.auth.spotify.SpotifyAuthFragment
import dev.timatifey.harmony.screen.home.groups.grouplist.GroupListFragment
import dev.timatifey.harmony.screen.home.profile.ProfileFragment
import dev.timatifey.harmony.screen.home.settings.SettingsFragment

@PresentationScope
@Subcomponent(modules = [])
interface PresentationComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(baseActivity: BaseActivity)

    fun inject(signInFragment: SignInFragment)

    fun inject(signUpFragment: SignUpFragment)

    fun inject(recoveryFragment: RecoveryFragment)

    fun inject(spotifyAuthFragment: SpotifyAuthFragment)

    fun inject(profileFragment: ProfileFragment)

    fun inject(groupListFragment: GroupListFragment)

    fun inject(settingsFragment: SettingsFragment)
}
