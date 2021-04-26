package dev.timatifey.harmony.screen.home.groups.newgroup

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.timatifey.harmony.R
import dev.timatifey.harmony.common.mvp.MvpViewObservableBase

class NewGroupMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?,
) : MvpViewObservableBase<NewGroupMvpView.Listener>(), NewGroupMvpView {
    override var rootView: View = layoutInflater.inflate(R.layout.fragment_new_group, parent, false)
}