package dev.timatifey.harmony.screen.home.group.share

import dev.timatifey.harmony.common.mvp.MvpViewObservable
import dev.timatifey.harmony.common.nav.BackPressedListener

interface ShareGroupMvpView: MvpViewObservable<ShareGroupMvpView.Listener> {
    interface Listener: BackPressedListener {
        fun onShareLinkBtnClicked()
        fun onCancelBtnClicked()
    }
    fun isVisibleLoading(isVisible: Boolean)
    fun disableAll()
    fun enableAll()
    fun setShareCodeText(link: String)
}