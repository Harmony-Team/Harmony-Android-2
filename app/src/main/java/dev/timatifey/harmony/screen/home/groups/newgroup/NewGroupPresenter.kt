package dev.timatifey.harmony.screen.home.groups.newgroup

import android.net.Uri
import dev.timatifey.harmony.common.mvp.MvpPresenter
import dev.timatifey.harmony.common.nav.AppScreenNavigator
import dev.timatifey.harmony.common.nav.BackPressDispatcher
import dev.timatifey.harmony.service.GroupService
import dev.timatifey.harmony.util.generateShareLink
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren

class NewGroupPresenter(
    private val appScreenNavigator: AppScreenNavigator,
    private val backPressDispatcher: BackPressDispatcher,
    private val groupService: GroupService,
    private val pickPhotoIntentListener: PickPhotoIntentListener,
) : MvpPresenter<NewGroupMvpView>, NewGroupMvpView.Listener {

    private lateinit var view: NewGroupMvpView
    private val coroutineScope = CoroutineScope(Dispatchers.Main.immediate + SupervisorJob())

    override fun bindView(view: NewGroupMvpView) {
        this.view = view
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
        coroutineScope.coroutineContext.cancelChildren()
    }

    override fun onCreateBtnClicked(name: String, description: String, imageUri: String?) {
        //TODO("Create group")
        val shareLink = generateShareLink(1)
        appScreenNavigator.toShareGroup(shareLink)
    }

    override fun onCancelBtnClicked() {
        appScreenNavigator.navigateUp()
    }

    override fun onPickImageBtnClicked() {
        pickPhotoIntentListener.startActivityForPickPhoto()
    }

    fun onPickPhotoResult(returnedImage: Uri) {
        view.selectPhoto(returnedImage)
    }

    override fun onBackPressed(): Boolean {
        appScreenNavigator.navigateUp()
        return true
    }
}