package dev.timatifey.harmony.screen.home.group.share

import android.content.Intent
import dev.timatifey.harmony.common.mvp.MvpPresenter
import dev.timatifey.harmony.common.nav.app.AppScreenNavigator
import dev.timatifey.harmony.common.nav.BackPressDispatcher

class ShareGroupPresenter(
    private val appScreenNavigator: AppScreenNavigator,
    private val backPressDispatcher: BackPressDispatcher,
    private val shareIntentListener: ShareIntentListener,
    private val shareCode: String,
    private val groupId: Long,
) : MvpPresenter<ShareGroupMvpView>, ShareGroupMvpView.Listener {

    private lateinit var view: ShareGroupMvpView

    override fun bindView(view: ShareGroupMvpView) {
        this.view = view
        initShareLink()
    }

    private fun initShareLink() {
        view.setShareCodeText(shareCode)
    }

    override fun onStart() {
        view.registerListener(this)
        backPressDispatcher.registerListener(this)
    }

    override fun onStop() {
        view.unregisterListener(this)
        backPressDispatcher.unregisterListener(this)
    }

    override fun onDestroy() {
    }

    override fun onShareLinkBtnClicked() {
        view.isVisibleLoading(true)
        view.disableAll()
        shareIntentListener.startActivityForShare(shareCode)
    }

    fun onShareResult(resultCode: Int, data: Intent?) {
        view.isVisibleLoading(false)
        view.enableAll()
        appScreenNavigator.toLobby(groupId)
    }

    override fun onCancelBtnClicked() {
        appScreenNavigator.navigateUp()
    }

    override fun onBackPressed(): Boolean {
        appScreenNavigator.navigateUp()
        return true
    }

}