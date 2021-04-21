package dev.timatifey.harmony.common.nav

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.ncapdevi.fragnav.FragNavController
import com.ncapdevi.fragnav.FragNavTransactionOptions
import dev.timatifey.harmony.R
import dev.timatifey.harmony.common.app.AppSettings
import dev.timatifey.harmony.di.scope.ActivityScope
import dev.timatifey.harmony.screen.activity.MainMvpView
import dev.timatifey.harmony.screen.auth.recovery.RecoveryFragment
import dev.timatifey.harmony.screen.auth.signin.SignInFragment
import dev.timatifey.harmony.screen.auth.signup.SignUpFragment
import dev.timatifey.harmony.screen.auth.spotify.SpotifyAuthFragment
import dev.timatifey.harmony.screen.home.groups.grouplist.GroupListFragment
import dev.timatifey.harmony.screen.home.profile.ProfileFragment
import dev.timatifey.harmony.screen.home.settings.SettingsFragment
import javax.inject.Inject

@ActivityScope
class AppScreenNavigator @Inject constructor(
    val appSettings: AppSettings,
    fragmentManager: FragmentManager,
    savedInstanceState: Bundle?
) : AppScreenRouter, FragNavController.RootFragmentListener {

    companion object {
        const val TAG = "AppScreenNavigator"
        const val INDEX_AUTH = FragNavController.TAB1
        const val INDEX_PROFILE = FragNavController.TAB2
        const val INDEX_GROUP_LIST = FragNavController.TAB3
        const val INDEX_SETTINGS = FragNavController.TAB4
    }

    private val fragNavController: FragNavController =
        FragNavController(fragmentManager, R.id.activity_main__container)

    val stackIsEmpty: Boolean
        get() = fragNavController.currentStack?.isEmpty() ?: true

    init {
        fragNavController.rootFragmentListener = this
        if (appSettings.isAuthorized) {
            fragNavController.initialize(INDEX_PROFILE, savedInstanceState)
        } else {
            fragNavController.initialize(INDEX_AUTH, savedInstanceState)
        }

    }

    override val numberOfRootFragments: Int
        get() = 4

    override fun getRootFragment(index: Int): Fragment {
        return when (index) {
            INDEX_AUTH -> SignInFragment.newInstance()
            INDEX_PROFILE -> ProfileFragment.newInstance()
            INDEX_GROUP_LIST -> GroupListFragment.newInstance()
            INDEX_SETTINGS -> SettingsFragment.newInstance()
            else -> throw IllegalStateException("Need to send an index that we know")
        }
    }

    override fun toHome() {
        toProfile()
    }

    override fun toAuth() {
        val options = transactionOptions(
            R.anim.fade_in,
            R.anim.fade_out
        )
        fragNavController.switchTab(INDEX_AUTH, options)
    }

    fun onSaveInstanceState(outState: Bundle?) {
        fragNavController.onSaveInstanceState(outState)
    }

    fun navigateUp() {
        val options = transactionOptions(
            R.anim.fade_in,
            R.anim.fade_out,
            R.anim.fade_in,
            R.anim.fade_out,
        )
        fragNavController.popFragment(options)
    }


    override fun toSignIn() {
        val options = transactionOptions(
            R.anim.fade_in,
            R.anim.fade_out
        )
        fragNavController.pushFragment(SignInFragment.newInstance(), options)
    }

    override fun toSignUp() {
        val options = transactionOptions(
            R.anim.fade_in,
            R.anim.fade_out
        )
        fragNavController.pushFragment(SignUpFragment.newInstance(), options)
    }

    override fun toRecovery() {
        val options = transactionOptions(
            R.anim.fade_in,
            R.anim.fade_out
        )
        fragNavController.pushFragment(RecoveryFragment.newInstance(), options)
    }

    override fun toSpotifyAuthWebView() {
        val options = transactionOptions(R.anim.fade_in, R.anim.fade_out)
        fragNavController.pushFragment(SpotifyAuthFragment.newInstance(), options)
    }

    override fun toProfile() {
        val options = transactionOptions(R.anim.fade_in, R.anim.fade_out)
        fragNavController.switchTab(INDEX_PROFILE, options)
    }

    override fun toSettings() {
        val options = transactionOptions(R.anim.fade_in, R.anim.fade_out)
        fragNavController.switchTab(INDEX_SETTINGS, options)
    }

    override fun toGroupList() {
        val options = transactionOptions(R.anim.fade_in, R.anim.fade_out)
        fragNavController.switchTab(INDEX_GROUP_LIST, options)
    }

    private fun transactionOptions(vararg animationIds: Int): FragNavTransactionOptions =
        if (animationIds.size == 2)
            FragNavTransactionOptions
                .newBuilder()
                .customAnimations(
                    animationIds[0], animationIds[1]
                )
                .build()
        else
            FragNavTransactionOptions
                .newBuilder()
                .customAnimations(
                    animationIds[0], animationIds[1], animationIds[2], animationIds[3]
                )
                .build()

}
