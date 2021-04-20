package dev.timatifey.harmony.common.mvp.factory

import android.view.LayoutInflater
import android.view.ViewGroup
import dev.timatifey.harmony.di.scope.PresentationScope
import dev.timatifey.harmony.screen.auth.recovery.RecoveryMvpView
import dev.timatifey.harmony.screen.auth.recovery.RecoveryMvpViewImpl
import dev.timatifey.harmony.screen.auth.signin.SignInMvpView
import dev.timatifey.harmony.screen.auth.signin.SignInMvpViewImpl
import dev.timatifey.harmony.screen.auth.signup.SignUpMvpView
import dev.timatifey.harmony.screen.auth.signup.SignUpMvpViewImpl
import dev.timatifey.harmony.screen.auth.spotify.SpotifyAuthMvpView
import dev.timatifey.harmony.screen.auth.spotify.SpotifyAuthMvpViewImpl
import javax.inject.Inject

@PresentationScope
class MvpViewFactory @Inject constructor(
    private val layoutInflater: LayoutInflater
) {

    fun createSignInMvpView(parent: ViewGroup?): SignInMvpView =
        SignInMvpViewImpl(layoutInflater, parent)

    fun createSignUpMvpView(parent: ViewGroup?): SignUpMvpView =
        SignUpMvpViewImpl(layoutInflater, parent)

    fun createRecoveryMvpView(parent: ViewGroup?): RecoveryMvpView =
        RecoveryMvpViewImpl(layoutInflater, parent)

    fun createSpotifyAuthMvpView(parent: ViewGroup?): SpotifyAuthMvpView =
        SpotifyAuthMvpViewImpl(layoutInflater, parent)

}