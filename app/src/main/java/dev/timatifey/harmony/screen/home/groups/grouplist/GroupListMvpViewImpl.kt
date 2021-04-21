package dev.timatifey.harmony.screen.home.groups.grouplist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.timatifey.harmony.R
import dev.timatifey.harmony.common.mvp.MvpViewObservableBase

class GroupListMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
): MvpViewObservableBase<GroupListMvpView.Listener>(), GroupListMvpView {
    override var rootView: View = layoutInflater.inflate(R.layout.fragment_group_list, parent, false)
}