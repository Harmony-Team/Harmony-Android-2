package dev.timatifey.harmony.screen.home.groups.sharegroup

import dev.timatifey.harmony.common.mvp.MvpPresenter
import dev.timatifey.harmony.common.nav.AppScreenNavigator
import dev.timatifey.harmony.common.nav.BackPressDispatcher
import dev.timatifey.harmony.util.generateShareLink

class ShareGroupPresenter(
    private val appScreenNavigator: AppScreenNavigator,
    private val backPressDispatcher: BackPressDispatcher,
    private val shareIntentListener: ShareIntentListener,
) : MvpPresenter<ShareGroupMvpView>, ShareGroupMvpView.Listener {

    private lateinit var view: ShareGroupMvpView
    private lateinit var shareLink: String

    override fun bindView(view: ShareGroupMvpView) {
        this.view = view
        initShareLink()
    }

    private fun initShareLink() {
        shareLink = generateShareLink(1)
        view.setShareLinkText(shareLink)
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
        shareIntentListener.startActivityForShare(shareLink)
    }

    override fun onCancelBtnClicked() {
        appScreenNavigator.navigateUp()
    }

    override fun onBackPressed(): Boolean {
        appScreenNavigator.navigateUp()
        return true
    }

}