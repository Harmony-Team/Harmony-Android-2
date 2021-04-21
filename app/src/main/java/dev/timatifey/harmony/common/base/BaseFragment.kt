package dev.timatifey.harmony.common.base

import android.content.Context
import androidx.fragment.app.Fragment
import dev.timatifey.harmony.screen.activity.DrawerDispatcher

abstract class BaseFragment : Fragment() {

    lateinit var drawerDispatcher: DrawerDispatcher

    protected val presentationComponent by lazy {
        (requireActivity() as BaseActivity).activityComponent
            .newPresentationComponent()
    }

    override fun onAttach(context: Context) {
        drawerDispatcher = activity as DrawerDispatcher
        super.onAttach(context)
    }
}
