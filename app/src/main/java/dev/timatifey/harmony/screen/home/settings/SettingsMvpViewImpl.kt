package dev.timatifey.harmony.screen.home.settings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.timatifey.harmony.R
import dev.timatifey.harmony.common.mvp.MvpViewObservableBase

class SettingsMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
): MvpViewObservableBase<SettingsMvpView.Listener>(), SettingsMvpView {
    override var rootView: View = layoutInflater.inflate(R.layout.fragment_settings, parent, false)
}