package dev.timatifey.harmony.common.mvp.factory

import android.view.LayoutInflater
import android.view.ViewGroup
import dev.timatifey.harmony.di.scope.PresentationScope
import dev.timatifey.harmony.screen.auth.signin.SignInMvpView
import dev.timatifey.harmony.screen.auth.signin.SignInMvpViewImpl
import dev.timatifey.harmony.screen.splash.SplashMvpView
import dev.timatifey.harmony.screen.splash.SplashMvpViewImpl
import javax.inject.Inject

@PresentationScope
class MvpViewFactory @Inject constructor(
    private val layoutInflater: LayoutInflater
) {

    fun createSignInMvpView(parent: ViewGroup?): SignInMvpView =
        SignInMvpViewImpl(layoutInflater, parent)

    fun createSplashMvpView(parent: ViewGroup?): SplashMvpView =
        SplashMvpViewImpl(layoutInflater, parent)
}