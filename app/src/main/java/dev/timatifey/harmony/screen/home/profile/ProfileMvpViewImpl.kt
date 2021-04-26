package dev.timatifey.harmony.screen.home.profile

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import dev.timatifey.harmony.R
import dev.timatifey.harmony.common.mvp.MvpViewObservableBase

class ProfileMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : MvpViewObservableBase<ProfileMvpView.Listener>(), ProfileMvpView {

    override var rootView: View = layoutInflater.inflate(R.layout.fragment_profile, parent, false)

    private val ibMenu: AppCompatImageButton = findViewById(R.id.fragment_home__profile__ic_menu)
    private val ivProfileImage: AppCompatImageView =
        findViewById(R.id.fragment_home__profile__image)
    private val tvUsername: AppCompatTextView =
        findViewById(R.id.fragment_home__profile__username_label)

    init {
        ibMenu.setOnClickListener {
            listeners.forEach { it.onMenuBtnClicked() }
        }
    }

    override fun setUsername(username: String) {
        tvUsername.text = username
    }

    override fun setImageProfile(imageUri: Uri) {
        ivProfileImage.setImageURI(imageUri)
    }

    override fun showMessage(msgId: Int) {
        showToast(msgId)
    }
}