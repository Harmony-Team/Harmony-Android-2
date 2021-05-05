package dev.timatifey.harmony.common.nav.lobby

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.ncapdevi.fragnav.FragNavController
import com.ncapdevi.fragnav.FragNavSwitchController
import com.ncapdevi.fragnav.FragNavTransactionOptions
import com.ncapdevi.fragnav.tabhistory.UnlimitedTabHistoryStrategy
import dev.timatifey.harmony.R
import dev.timatifey.harmony.lobby.LobbyState
import dev.timatifey.harmony.lobby.LobbyStateMachine
import dev.timatifey.harmony.screen.home.lobby.tabs.LobbyTabsFragment
import dev.timatifey.harmony.screen.home.lobby.waiting.WaitingPlaylistFragment

class LobbyFragmentNavigator(
    val groupId: Long,
    val lobbyState: LobbyStateMachine,
    fragmentManager: FragmentManager,
    savedInstanceState: Bundle?
) : LobbyFragmentRouter, FragNavController.RootFragmentListener {

    companion object {
        const val TAG = "LobbyFragmentNavigator"
        const val INDEX_TABS = FragNavController.TAB1
        const val INDEX_WAITING = FragNavController.TAB2
    }

    private val fragNavController: FragNavController =
        FragNavController(fragmentManager, R.id.fragment_lobby__container)

    init {
        fragNavController.apply {
            rootFragmentListener = this@LobbyFragmentNavigator
            navigationStrategy = UnlimitedTabHistoryStrategy(object : FragNavSwitchController {
                override fun switchTab(index: Int, transactionOptions: FragNavTransactionOptions?) {
                    fragNavController.switchTab(index, transactionOptions)
                }
            })
            defaultTransactionOptions = transactionOptions(
                R.anim.fade_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.fade_out,
            )
            when (lobbyState.state) {
                is LobbyState.Unready -> initialize(INDEX_TABS, savedInstanceState)
                is LobbyState.Waiting -> initialize(INDEX_WAITING, savedInstanceState)
            }
        }

    }

    override val numberOfRootFragments: Int
        get() = 2

    override fun getRootFragment(index: Int): Fragment {
        return when (index) {
            INDEX_TABS -> LobbyTabsFragment.newInstance()
            INDEX_WAITING -> WaitingPlaylistFragment.newInstance()
            else -> throw IllegalStateException("Need to send an index that we know")
        }
    }

    fun onSaveInstanceState(outState: Bundle?) {
        fragNavController.onSaveInstanceState(outState)
    }

    fun clearBackStack() {
        fragNavController.clearStack()
    }

    fun navigateUp() {
        val options = transactionOptions(
            R.anim.fade_in,
            R.anim.fade_out,
            R.anim.fade_in,
            R.anim.fade_out,
        )
        fragNavController.popFragment(options)
    }

    private fun transactionOptions(vararg animationIds: Int): FragNavTransactionOptions =
        if (animationIds.size == 2)
            FragNavTransactionOptions
                .newBuilder()
                .customAnimations(
                    animationIds[0], animationIds[1]
                )
                .build()
        else
            FragNavTransactionOptions
                .newBuilder()
                .customAnimations(
                    animationIds[0], animationIds[1], animationIds[2], animationIds[3]
                )
                .build()


    override fun toReadyStateFragment() {
        fragNavController.switchTab(INDEX_WAITING)
    }

    override fun toUnreadyStateFragment() {
        fragNavController.switchTab(INDEX_TABS)
    }

}
