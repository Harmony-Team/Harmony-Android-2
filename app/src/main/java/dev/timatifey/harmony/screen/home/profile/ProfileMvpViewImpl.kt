package dev.timatifey.harmony.screen.home.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.timatifey.harmony.R
import dev.timatifey.harmony.common.mvp.MvpViewObservableBase

class ProfileMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
): MvpViewObservableBase<ProfileMvpView.Listener>(), ProfileMvpView {
    override var rootView: View = layoutInflater.inflate(R.layout.fragment_profile, parent, false)
}