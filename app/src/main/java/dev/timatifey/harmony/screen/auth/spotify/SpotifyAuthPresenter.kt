package dev.timatifey.harmony.screen.auth.spotify

import android.net.Uri
import android.webkit.WebResourceRequest
import dev.timatifey.harmony.R
import dev.timatifey.harmony.api.spotify.utils.constructAuthorizationURI
import dev.timatifey.harmony.common.app.Config
import dev.timatifey.harmony.common.mvp.MvpPresenter
import dev.timatifey.harmony.common.nav.BackPressDispatcher
import dev.timatifey.harmony.common.nav.AppScreenNavigator
import dev.timatifey.harmony.data.Status
import dev.timatifey.harmony.service.AuthService
import dev.timatifey.harmony.service.UserService
import dev.timatifey.harmony.util.randomString
import kotlinx.coroutines.*

class SpotifyAuthPresenter(
    private val appScreenNavigator: AppScreenNavigator,
    private val backPressDispatcher: BackPressDispatcher,
    private val authService: AuthService,
    private val userService: UserService,
) : MvpPresenter<SpotifyAuthMvpView>, SpotifyAuthMvpView.Listener {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private lateinit var view: SpotifyAuthMvpView
    private val spotifyCodeVerifier = randomString(43, 128)

    override fun bindView(view: SpotifyAuthMvpView) {
        this.view = view
        view.showLoading()
        view.loadUrl(constructAuthorizationURI(spotifyCodeVerifier))
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

    override fun onOverrideUrlLoading(request: WebResourceRequest) {
        val url: String = request.url.toString()
        if (url.contains(Config.SPOTIFY_REDIRECT_URI)) {
            val uri: Uri = Uri.parse(url)
            val code = uri.getQueryParameter("code")
            if (code != null) {
                runBlocking {
                    val result = authService.exchangesCodeForAccessToken(code, spotifyCodeVerifier)
                    if (result.status is Status.Success) {
                        view.showMessage(R.string.success_spotify_auth)
                        userService.integrateSpotify()
                    } else {
                        view.showMessage(R.string.auth_failed)
                    }
                }
            } else {
                view.showMessage(R.string.auth_failed)
            }
            appScreenNavigator.navigateUp()
        } else {
            view.showLoading()
            view.loadUrl(url)
        }
    }

    override fun onBackPressed(): Boolean {
        view.hideWebView()
        view.hideLoading()
        appScreenNavigator.navigateUp()
        return true
    }
}