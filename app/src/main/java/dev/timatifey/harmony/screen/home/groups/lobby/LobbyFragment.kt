package dev.timatifey.harmony.screen.home.groups.lobby

import android.os.Bundle
import androidx.fragment.app.Fragment
import dev.timatifey.harmony.common.base.BaseFragment

class LobbyFragment: BaseFragment() {

    companion object {
        const val ARG_GROUP = "ARG_GROUP"

        fun newInstance(groupId: Long): Fragment {
            val fragment: Fragment = LobbyFragment()
            val args: Bundle = Bundle()
            args.putLong(ARG_GROUP, groupId)
            fragment.arguments = args
            return fragment
        }
    }
}