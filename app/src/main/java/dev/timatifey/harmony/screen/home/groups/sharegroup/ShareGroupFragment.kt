package dev.timatifey.harmony.screen.home.groups.sharegroup

import android.os.Bundle
import androidx.fragment.app.Fragment
import dev.timatifey.harmony.common.base.BaseFragment

class ShareGroupFragment: BaseFragment() {

    companion object {
        const val ARG_LINK = "ARG_LINk"
        fun newInstance(link: String): Fragment {
            val fragment: Fragment = ShareGroupFragment()
            val args: Bundle = Bundle()
            args.putString(ARG_LINK, link)
            fragment.arguments = args
            return fragment
        }
    }
}