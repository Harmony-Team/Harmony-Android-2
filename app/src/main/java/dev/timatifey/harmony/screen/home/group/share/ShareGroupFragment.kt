package dev.timatifey.harmony.screen.home.group.share

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.timatifey.harmony.common.base.BaseFragment
import dev.timatifey.harmony.util.createShareLinkIntent

class ShareGroupFragment: BaseFragment(), ShareIntentListener {

    private lateinit var presenter: ShareGroupPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val link = arguments!!.getString(ARG_LINK)!!
        val groupId = arguments!!.getLong(ARG_GROUP_ID)!!
        presenter = presenterFactory.createShareGroupPresenter(this, link, groupId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: ShareGroupMvpView = mvpViewFactory.createShareGroupMvpView(container)
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

    override fun startActivityForShare(code: String) {
        startActivity(Intent.createChooser(createShareLinkIntent(code), "Choose messenger"))
    }

    companion object {
        const val ARG_LINK = "ARG_LINk"
        const val ARG_GROUP_ID = "ARG_GROUP_ID"

        fun newInstance(link: String, groupId: Long): Fragment {
            val fragment: Fragment = ShareGroupFragment()
            val args: Bundle = Bundle()
            args.putString(ARG_LINK, link)
            args.putLong(ARG_GROUP_ID, groupId)
            fragment.arguments = args
            return fragment
        }
    }
}