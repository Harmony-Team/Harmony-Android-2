package dev.timatifey.harmony.screen.splash

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.timatifey.harmony.R

class SplashMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : SplashMvpView {
    override var rootView: View = layoutInflater.inflate(R.layout.fragment_splash, parent, false)
}