package dev.timatifey.harmony.screen.home.group.add

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageButton
import dev.timatifey.harmony.R
import dev.timatifey.harmony.common.mvp.MvpViewObservableBase

class AddGroupMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?,
): MvpViewObservableBase<AddGroupMvpView.Listener>(), AddGroupMvpView {
    
    override var rootView: View = layoutInflater.inflate(R.layout.fragment_add_group, parent, false)
    private val btnNewGroup: AppCompatButton = findViewById(R.id.fragment_add_group__new_group_btn)
    private val btnJoinGroup: AppCompatButton = findViewById(R.id.fragment_add_group__join_group_btn)
    private val ibCancel: AppCompatImageButton = findViewById(R.id.fragment_add_group__cancel)
    
    init {
        btnNewGroup.setOnClickListener { listeners.forEach { it.onNewGroupClicked() } }
        btnJoinGroup.setOnClickListener { listeners.forEach { it.onJoinGroupClicked() } }
        ibCancel.setOnClickListener { listeners.forEach { it.onCancelClicked() } }
    }
}