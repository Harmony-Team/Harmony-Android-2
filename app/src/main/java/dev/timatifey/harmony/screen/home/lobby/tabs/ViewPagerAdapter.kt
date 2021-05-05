package dev.timatifey.harmony.screen.home.lobby.tabs

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import dev.timatifey.harmony.screen.home.lobby.tabs.music.MusicFragment
import dev.timatifey.harmony.screen.home.lobby.tabs.playlists.PlaylistsFragment

class ViewPagerAdapter(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> MusicFragment.newInstance()
            1 -> PlaylistsFragment.newInstance()
            else -> throw IllegalStateException("Need to send an index that we know")
        }
}