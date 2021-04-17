package dev.timatifey.harmony.screen.common.base

import androidx.fragment.app.Fragment
import dev.timatifey.harmony.App
import dev.timatifey.harmony.screen.common.MainActivity

abstract class BaseFragment : Fragment() {

    protected val app: App get() = activity?.application as App

    protected val mainActivity get() = activity as MainActivity
}
