package dev.timatifey.harmony.screen.home.groups.joingroup

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.timatifey.harmony.R
import dev.timatifey.harmony.common.mvp.MvpViewObservableBase

class JoinGroupMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?,
) : MvpViewObservableBase<JoinGroupMvpView.Listener>(), JoinGroupMvpView {
    override var rootView: View = layoutInflater.inflate(R.layout.fragment_join_group, parent, false)
}