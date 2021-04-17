package dev.timatifey.harmony.screen.common.nav

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ncapdevi.fragnav.FragNavController

class ScreenNavigator(
    private val fragNavController: FragNavController,
    savedInstanceState: Bundle?
) : FragNavController.RootFragmentListener {

    init {
        fragNavController.rootFragmentListener = this
        fragNavController.initialize(FragNavController.TAB1, savedInstanceState);
    }


    override val numberOfRootFragments: Int
        get() = 1

    override fun getRootFragment(index: Int): Fragment {
        TODO("Not yet implemented")
    }


    fun onSaveInstanceState(outState: Bundle?) {
        fragNavController.onSaveInstanceState(outState)
    }

    fun navigateUp() {
        fragNavController.popFragment()
    }

}
