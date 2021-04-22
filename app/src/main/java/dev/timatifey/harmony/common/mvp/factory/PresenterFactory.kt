package dev.timatifey.harmony.common.mvp.factory

import dev.timatifey.harmony.screen.auth.signin.SignInPresenter
import dev.timatifey.harmony.common.nav.BackPressDispatcher
import dev.timatifey.harmony.common.nav.AppScreenNavigator
import dev.timatifey.harmony.di.scope.ActivityScope
import dev.timatifey.harmony.di.scope.PresentationScope
import dev.timatifey.harmony.screen.activity.MainPresenter
import dev.timatifey.harmony.screen.auth.recovery.RecoveryPresenter
import dev.timatifey.harmony.screen.auth.signup.SignUpPresenter
import dev.timatifey.harmony.screen.auth.spotify.SpotifyAuthPresenter
import dev.timatifey.harmony.screen.home.profile.ProfilePresenter
import dev.timatifey.harmony.screen.home.settings.SettingsPresenter
import dev.timatifey.harmony.service.AuthService
import dev.timatifey.harmony.service.UserService
import javax.inject.Inject

@PresentationScope
class PresenterFactory @Inject constructor(
    private val appScreenNavigator: AppScreenNavigator,
    private val backPressDispatcher: BackPressDispatcher,
    private val authService: AuthService,
    private val userService: UserService,
) {

    fun createSignInPresenter(): SignInPresenter =
        SignInPresenter(
            appScreenNavigator = appScreenNavigator,
            authService = authService,
            backPressDispatcher = backPressDispatcher
        )

    fun createSignUpPresenter(): SignUpPresenter =
        SignUpPresenter(
            appScreenNavigator = appScreenNavigator,
            authService = authService,
            backPressDispatcher = backPressDispatcher
        )

    fun createRecoveryPresenter(): RecoveryPresenter =
        RecoveryPresenter(
            appScreenNavigator = appScreenNavigator,
            authService = authService,
            backPressDispatcher = backPressDispatcher
        )

    fun createSpotifyAuthPresenter(): SpotifyAuthPresenter =
        SpotifyAuthPresenter(
            appScreenNavigator = appScreenNavigator,
            backPressDispatcher = backPressDispatcher,
            authService = authService,
            userService = userService,
        )

    fun createMainPresenter(): MainPresenter =
        MainPresenter(
            appScreenNavigator = appScreenNavigator,
            authService = authService,
            backPressDispatcher = backPressDispatcher,
        )

    fun createProfilePresenter(): ProfilePresenter =
        ProfilePresenter(
            userService = userService,
            backPressDispatcher = backPressDispatcher,
        )

    fun createSettingsPresenter(): SettingsPresenter =
        SettingsPresenter(
            appScreenNavigator = appScreenNavigator,
            backPressDispatcher = backPressDispatcher,
            userService = userService,
        )
}