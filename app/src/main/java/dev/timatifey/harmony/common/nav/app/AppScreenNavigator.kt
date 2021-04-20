package dev.timatifey.harmony.common.nav.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ncapdevi.fragnav.FragNavController
import dev.timatifey.harmony.data.model.harmony.Token
import dev.timatifey.harmony.di.scope.ActivityScope
import dev.timatifey.harmony.screen.auth.signin.SignInFragment
import dev.timatifey.harmony.screen.splash.SplashFragment
import javax.inject.Inject

@ActivityScope
class AppScreenNavigator @Inject constructor(
    private val fragNavController: FragNavController,
    savedInstanceState: Bundle?
) : AppScreenRouter, FragNavController.RootFragmentListener {

    init {
        fragNavController.rootFragmentListener = this
        fragNavController.initialize(FragNavController.TAB1, savedInstanceState);
    }

    override val numberOfRootFragments: Int
        get() = 1

    override fun getRootFragment(index: Int): Fragment {
        return SplashFragment.newInstance()
    }

    fun onSaveInstanceState(outState: Bundle?) {
        fragNavController.onSaveInstanceState(outState)
    }

    fun navigateUp() {
        fragNavController.popFragment()
    }

    override fun fromSplashToAuth() {
        fragNavController.replaceFragment(SignInFragment.newInstance())
    }

    override fun fromSplashToHome() {
        TODO()
    }

    override fun navigateToSignInFragment() {
        fragNavController.pushFragment(SignInFragment.newInstance())
    }

    override fun navigateToSignUpFragment() {
        TODO("Not yet implemented")
    }

    override fun navigateToRecoveryFragment() {
        TODO("Not yet implemented")
    }

    override fun navigateToSpotifyAuthWebView() {
        TODO("Not yet implemented")
    }

    override fun navigateToHomeScreen(token: Token) {
        TODO("Not yet implemented")
    }

}
