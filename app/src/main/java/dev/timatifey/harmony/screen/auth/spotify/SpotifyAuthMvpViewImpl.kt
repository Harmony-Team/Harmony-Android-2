package dev.timatifey.harmony.screen.auth.spotify

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.Toast
import dev.timatifey.harmony.R
import dev.timatifey.harmony.common.mvp.MvpViewObservableBase

@SuppressLint("SetJavaScriptEnabled")
class SpotifyAuthMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : MvpViewObservableBase<SpotifyAuthMvpView.Listener>(), SpotifyAuthMvpView {

    override var rootView: View =
        layoutInflater.inflate(R.layout.fragment_spotify_auth, parent, false)

    private val pbLoading: ProgressBar = findViewById(R.id.fragment_spotify_auth__loading)
    private val webView: WebView = findViewById(R.id.fragment_spotify_auth__web_view)

    init {
        webView.apply {
            settings.javaScriptCanOpenWindowsAutomatically = true
            settings.javaScriptEnabled = true
            clearCache(true)
            webViewClient = SpotifyWebViewClient()
        }
    }

    override fun showLoading() {
        pbLoading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pbLoading.visibility = View.GONE
    }

    override fun showWebView() {
        webView.visibility = View.VISIBLE
    }

    override fun hideWebView() {
        webView.visibility = View.GONE
    }

    override fun showMessage(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    override fun showMessage(msgId: Int) {
        Toast.makeText(context, getString(msgId), Toast.LENGTH_SHORT).show()
    }

    override fun loadUrl(url: String) {
        webView.loadUrl(url)
    }

    inner class SpotifyWebViewClient : WebViewClient() {

        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            if (request != null) {
                listeners.forEach {
                    it.onOverrideUrlLoading(request)
                }
            }
            return false
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            hideLoading()
            showWebView()
        }

        override fun onPageFinished(view: WebView?, url: String) {
            super.onPageFinished(view, url)
        }
    }

}