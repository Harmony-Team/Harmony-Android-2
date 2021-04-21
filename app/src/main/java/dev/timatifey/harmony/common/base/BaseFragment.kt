package dev.timatifey.harmony.common.base

import android.content.Context
import androidx.fragment.app.Fragment
import dev.timatifey.harmony.screen.activity.DrawerLocker

abstract class BaseFragment : Fragment() {

    lateinit var drawerLocker: DrawerLocker

    protected val presentationComponent by lazy {
        (requireActivity() as BaseActivity).activityComponent
            .newPresentationComponent()
    }

    override fun onAttach(context: Context) {
        drawerLocker = activity as DrawerLocker
        super.onAttach(context)
    }
}
