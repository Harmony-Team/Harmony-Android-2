package dev.timatifey.harmony.screen.home.group.create

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.timatifey.harmony.common.base.BaseFragment

class NewGroupFragment : BaseFragment(), PickPhotoIntentListener {
    private lateinit var presenter: NewGroupPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = presenterFactory.createNewGroupPresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: NewGroupMvpView = mvpViewFactory.createNewGroupMvpView(container)
        presenter.bindView(view)
        return view.rootView
    }

    override fun onStart() {
        presenter.onStart()
        super.onStart()
    }

    override fun onStop() {
        presenter.onStop()
        super.onStop()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    companion object {
        fun newInstance(): Fragment {
            return NewGroupFragment()
        }
    }

    override fun startActivityForPickPhoto() {
        val pickPhoto = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        startActivityForResult(pickPhoto, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, imageReturnedIntent: Intent?) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent)
        val resultIntent = imageReturnedIntent ?: return
        when (requestCode) {
            1 -> {
                val selectedImage = resultIntent.data ?: return
                presenter.onPickPhotoResult(selectedImage)
            }
        }
    }

}