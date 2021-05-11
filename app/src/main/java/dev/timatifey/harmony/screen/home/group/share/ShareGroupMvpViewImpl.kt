package dev.timatifey.harmony.screen.home.group.share

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
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
    private val loading: ProgressBar = findViewById(R.id.fragment_share_group__loading)

    init {
        btnShare.setOnClickListener { listeners.forEach { it.onShareLinkBtnClicked() } }
        tvCancel.setOnClickListener { listeners.forEach { it.onCancelBtnClicked() } }
    }

    override fun isVisibleLoading(isVisible: Boolean) {
        if (isVisible) {
            loading.visibility = View.VISIBLE
        } else {
            loading.visibility = View.GONE
        }
    }

    override fun disableAll() {
        btnShare.isEnabled = false
        tvCancel.isEnabled = false
    }

    override fun enableAll() {
        btnShare.isEnabled = true
        tvCancel.isEnabled = true
    }

    override fun setShareCodeText(link: String) {
        tvShareLink.text = link
    }

}