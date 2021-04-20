package dev.timatifey.harmony.common.nav.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.ncapdevi.fragnav.FragNavController
import com.ncapdevi.fragnav.FragNavTransactionOptions
import dev.timatifey.harmony.R
import dev.timatifey.harmony.common.app.AppSettings
import dev.timatifey.harmony.data.model.harmony.Token
import dev.timatifey.harmony.di.scope.ActivityScope
import dev.timatifey.harmony.screen.auth.signin.SignInFragment
import dev.timatifey.harmony.screen.auth.signup.SignUpFragment
import dev.timatifey.harmony.screen.home.HomeFragment
import javax.inject.Inject

@ActivityScope
class AppScreenNavigator @Inject constructor(
    private val appSettings: AppSettings,
    fragmentManager: FragmentManager,
    savedInstanceState: Bundle?
) : AppScreenRouter, FragNavController.RootFragmentListener {

    companion object {
        const val TAG = "AppScreenNavigator"
        const val INDEX_AUTH = FragNavController.TAB1
        const val INDEX_HOME = FragNavController.TAB2
    }

    private val fragNavController: FragNavController =
        FragNavController(fragmentManager, R.id.container)

    init {
        fragNavController.rootFragmentListener = this
        fragNavController.initialize(
            if (appSettings.isAuthorized)
                INDEX_HOME
            else INDEX_AUTH,
            savedInstanceState
        )
    }

    override val numberOfRootFragments: Int
        get() = 2

    override fun getRootFragment(index: Int): Fragment {
        return when (index) {
            INDEX_AUTH -> SignInFragment.newInstance()
            INDEX_HOME -> HomeFragment.newInstance(appSettings.harmonyToken!!)
            else -> throw IllegalStateException("Need to send an index that we know")
        }
    }

    fun onSaveInstanceState(outState: Bundle?) {
        fragNavController.onSaveInstanceState(outState)
    }

    fun navigateUp() {
        val options = FragNavTransactionOptions
            .newBuilder()
            .customAnimations(
                R.anim.enter_from_right,
                R.anim.exit_to_left,
                R.anim.enter_from_left,
                R.anim.exit_to_right,
            )
            .build()
        fragNavController.popFragment(options)
    }

    override fun toSignIn() {
        val options = FragNavTransactionOptions
            .newBuilder()
            .customAnimations(
                R.anim.enter_from_left,
                R.anim.exit_to_right,
                R.anim.enter_from_right,
                R.anim.exit_to_left,
            )
            .build()
        fragNavController.pushFragment(SignInFragment.newInstance(), options)
    }

    override fun toSignUp() {
        val options = FragNavTransactionOptions
            .newBuilder()
            .customAnimations(
                R.anim.enter_from_right,
                R.anim.exit_to_left,
                R.anim.enter_from_left,
                R.anim.exit_to_right,
            )
            .build()
        fragNavController.pushFragment(SignUpFragment.newInstance(), options)
    }

    override fun toRecovery() {
        TODO("Not yet implemented")
    }

    override fun toSpotifyAuthWebView() {
        TODO("Not yet implemented")
    }

    override fun toHome(token: Token) {
        fragNavController.pushFragment(HomeFragment.newInstance(token))
    }

}
