package dev.timatifey.harmony.screen.auth.spotify

import android.webkit.WebResourceRequest
import dev.timatifey.harmony.common.mvp.MvpViewObservable
import dev.timatifey.harmony.common.nav.BackPressedListener

interface SpotifyAuthMvpView: MvpViewObservable<SpotifyAuthMvpView.Listener> {

    interface Listener : BackPressedListener {
        fun onOverrideUrlLoading(request: WebResourceRequest)
    }

    fun loadUrl(url: String)
    fun showLoading()
    fun hideLoading()
    fun showWebView()
    fun hideWebView()

    fun showMessage(msg: String)
    fun showMessage(msgId: Int)
}