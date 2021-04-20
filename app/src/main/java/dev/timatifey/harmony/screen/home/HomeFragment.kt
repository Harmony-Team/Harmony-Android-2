package dev.timatifey.harmony.screen.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import dev.timatifey.harmony.common.base.BaseFragment
import dev.timatifey.harmony.data.model.harmony.Token

class HomeFragment: BaseFragment() {

    companion object {

        const val ARG_TOKEN = "token"

        fun newInstance(token: Token): Fragment {
            val fragment: Fragment = HomeFragment()
            val args = Bundle()
            args.putCharSequence(ARG_TOKEN, token.toString())
            fragment.arguments = args
            return fragment
        }
    }
}