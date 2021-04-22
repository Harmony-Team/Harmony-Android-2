package dev.timatifey.harmony.screen.home.settings

import dev.timatifey.harmony.common.mvp.MvpPresenter
import dev.timatifey.harmony.common.nav.AppScreenNavigator
import dev.timatifey.harmony.common.nav.BackPressDispatcher
import dev.timatifey.harmony.data.Status
import dev.timatifey.harmony.service.UserService
import kotlinx.coroutines.*

class SettingsPresenter(
    private val backPressDispatcher: BackPressDispatcher,
    private val appScreenNavigator: AppScreenNavigator,
    private val userService: UserService,
): MvpPresenter<SettingsMvpView>, SettingsMvpView.Listener {

    private lateinit var view: SettingsMvpView

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    override fun bindView(view: SettingsMvpView) {
        this.view = view
        initSettings()
    }

    private fun initSettings() {
        val settings = userService.getUserSettings().data
        if (settings != null) {
            view.setEmailText(settings.email)
            view.setPasswordText(settings.password)
            view.setSpotifySwitchState(settings.isSpotifyAuth)
        }
    }

    override fun onStart() {
        view.registerListener(this)
        backPressDispatcher.registerListener(this)
    }

    override fun onStop() {
        view.unregisterListener(this)
        backPressDispatcher.unregisterListener(this)
    }

    override fun onDestroy() {
        coroutineScope.coroutineContext.cancelChildren()
    }

    override fun onEmailEditClicked() {
        view.showMessage("Edit email todo")
    }

    override fun onPasswordEditClicked() {
        view.showMessage("Edit password todo")
    }

    override fun onSpotifySwitchClicked(isChecked: Boolean) {
        if (isChecked) {
            appScreenNavigator.toSpotifyAuthWebView()
        } else {
            coroutineScope.launch {
                userService.disintegrateSpotify()
            }
        }
    }

    override fun onBackPressed(): Boolean {
        appScreenNavigator.navigateUp()
        return true
    }

}