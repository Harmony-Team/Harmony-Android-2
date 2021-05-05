package dev.timatifey.harmony.screen.home.group.share

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import dev.timatifey.harmony.R
import dev.timatifey.harmony.common.mvp.MvpViewObservableBase

class ShareGroupMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?,
) : MvpViewObservableBase<ShareGroupMvpView.Listener>(), ShareGroupMvpView {

    override var rootView: View =
        layoutInflater.inflate(R.layout.fragment_share_group, parent, false)
    private val btnShare: AppCompatButton =
        findViewById(R.id.fragment_share_group__share_btn)
    private val tvCancel: AppCompatTextView =
        findViewById(R.id.fragment_share_group__cancel_btn)
    private val tvShareLink: AppCompatTextView =
        findViewById(R.id.fragment_share_group__link)

    init {
        btnShare.setOnClickListener { listeners.forEach { it.onShareLinkBtnClicked() } }
        tvCancel.setOnClickListener { listeners.forEach { it.onCancelBtnClicked() } }
    }

    override fun setShareCodeText(link: String) {
        tvShareLink.text = link
    }

}