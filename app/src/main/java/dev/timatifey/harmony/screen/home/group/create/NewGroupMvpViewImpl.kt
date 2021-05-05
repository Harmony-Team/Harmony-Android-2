package dev.timatifey.harmony.screen.home.group.create

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dev.timatifey.harmony.R
import dev.timatifey.harmony.common.mvp.MvpViewObservableBase

class NewGroupMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?,
) : MvpViewObservableBase<NewGroupMvpView.Listener>(), NewGroupMvpView {

    override var rootView: View = layoutInflater.inflate(R.layout.fragment_new_group, parent, false)
    private val btnCreateGroup: AppCompatButton =
        findViewById(R.id.fragment_new_group__create_btn)
    private val tvCancel: AppCompatTextView =
        findViewById(R.id.fragment_new_group__cancel_btn)
    private val etGroupName: AppCompatEditText =
        findViewById(R.id.fragment_new_group__new_group__name)
    private val etDescription: AppCompatEditText =
        findViewById(R.id.fragment_new_group__description)
    private val ivGroupImage: AppCompatImageView =
        findViewById(R.id.fragment_new_group__groupImage)
    private val fabPickImage: FloatingActionButton =
        findViewById(R.id.fragment_new_group__fab_pick_picture)
    private val ivImageLogo: AppCompatImageView =
        findViewById(R.id.fragment_new_group__imageLogo)

    private var selectedImage: Uri? = null

    init {
        btnCreateGroup.setOnClickListener {
            listeners.forEach {
                it.onCreateBtnClicked(
                    name = etGroupName.text.toString(),
                    description = etDescription.text.toString(),
                    imageUri = if (selectedImage != null) selectedImage.toString() else null
                )
            }
        }
        tvCancel.setOnClickListener {
            listeners.forEach {
                it.onCancelBtnClicked()
            }
        }
        fabPickImage.setOnClickListener {
            listeners.forEach {
                it.onPickImageBtnClicked()
            }
        }
    }

    override fun selectPhoto(selectedImage: Uri?) {
        this.selectedImage = selectedImage ?: return
        ivGroupImage.setImageURI(selectedImage)
        ivImageLogo.visibility = View.INVISIBLE
    }
}