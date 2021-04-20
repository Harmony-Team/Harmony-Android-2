package dev.timatifey.harmony.screen.splash

import dev.timatifey.harmony.data.Status
import dev.timatifey.harmony.common.mvp.MvpPresenter
import dev.timatifey.harmony.common.nav.app.AppScreenNavigator
import dev.timatifey.harmony.service.AuthService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class SplashPresenter(
    private val appScreenNavigator: AppScreenNavigator,
    private val authService: AuthService
): MvpPresenter<SplashMvpView> {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private lateinit var view: SplashMvpView

    override fun bindView(view: SplashMvpView) {
        this.view = view
        tryAuth()
    }

    private fun tryAuth() {
        coroutineScope.launch {
            val result = authService.authCacheIsValid()
            when (result.status) {
                is Status.Success -> {
                    appScreenNavigator.navigateToHomeScreen(result.data!!)
                }
                is Status.Error -> {
                    appScreenNavigator.navigateToSignInFragment()
                }
            }
        }
    }

    override fun onStart() {
    }

    override fun onStop() {
    }

    override fun onDestroy() {
    }
}