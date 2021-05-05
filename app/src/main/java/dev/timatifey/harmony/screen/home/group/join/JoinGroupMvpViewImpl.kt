package dev.timatifey.harmony.screen.home.group.join

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import dev.timatifey.harmony.R
import dev.timatifey.harmony.common.mvp.MvpViewObservableBase

class JoinGroupMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?,
) : MvpViewObservableBase<JoinGroupMvpView.Listener>(), JoinGroupMvpView {

    override var rootView: View =
        layoutInflater.inflate(R.layout.fragment_join_group, parent, false)
    private val btnJoinGroup: AppCompatButton = findViewById(R.id.fragment_join_group__join_btn)
    private val tvCancel: AppCompatTextView = findViewById(R.id.fragment_join_group__cancel_btn)
    private val etCode: AppCompatEditText = findViewById(R.id.fragment_join_group__code)

    init {
        btnJoinGroup.setOnClickListener {
            listeners.forEach { it.onJoinBtnClicked(etCode.text.toString()) }
        }
        tvCancel.setOnClickListener { listeners.forEach { it.onCancelBtnClicked() } }
    }
}