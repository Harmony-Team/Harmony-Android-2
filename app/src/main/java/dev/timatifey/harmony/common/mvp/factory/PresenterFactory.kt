package dev.timatifey.harmony.common.mvp.factory

import dev.timatifey.harmony.di.scope.PresentationScope
import dev.timatifey.harmony.screen.auth.signin.SignInPresenter
import dev.timatifey.harmony.common.nav.BackPressDispatcher
import dev.timatifey.harmony.common.nav.app.AppScreenNavigator
import dev.timatifey.harmony.screen.auth.signup.SignUpPresenter
import dev.timatifey.harmony.service.AuthService
import javax.inject.Inject

@PresentationScope
class PresenterFactory @Inject constructor(
    private val appScreenNavigator: AppScreenNavigator,
    private val backPressDispatcher: BackPressDispatcher,
    private val authService: AuthService
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
}