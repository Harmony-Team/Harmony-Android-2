package dev.timatifey.harmony.screen.home.group.create

import android.net.Uri
import dev.timatifey.harmony.common.mvp.MvpPresenter
import dev.timatifey.harmony.common.nav.app.AppScreenNavigator
import dev.timatifey.harmony.common.nav.BackPressDispatcher
import dev.timatifey.harmony.data.Status
import dev.timatifey.harmony.service.GroupService
import kotlinx.coroutines.*

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
        coroutineScope.launch {
            val groupRes = groupService.addGroup(name, description, imageUri)
            if (groupRes.status is Status.Success) {
                val group = groupRes.data!!
                appScreenNavigator.toShareGroup(group.second!!, group.first.id)
            } else {
                appScreenNavigator.toGroupList()
            }
        }
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