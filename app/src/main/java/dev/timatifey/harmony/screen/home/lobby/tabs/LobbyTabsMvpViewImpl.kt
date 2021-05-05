package dev.timatifey.harmony.screen.home.lobby.tabs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageButton
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dev.timatifey.harmony.R
import dev.timatifey.harmony.common.mvp.MvpViewObservableBase

class LobbyTabsMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?,
    viewPagerAdapter: ViewPagerAdapter,
) : MvpViewObservableBase<LobbyTabsMvpView.Listener>(), LobbyTabsMvpView, LobbyTabsPageController {

    override fun setMusicPage() {
        viewPager.currentItem = 0
    }

    override fun setPlaylistsPage() {
        viewPager.currentItem = 1
    }


    override var rootView: View =
        layoutInflater.inflate(R.layout.fragment_lobby_tabs, parent, false)
    private val viewPager: ViewPager2 = findViewById(R.id.fragment_lobby_tabs__view_pager)
    private val tabLayout: TabLayout = findViewById(R.id.fragment_lobby_tabs__tab_layout)
    private val ibSearch: AppCompatImageButton = findViewById(R.id.fragment_lobby_tabs__ic_search)

    init {
        viewPager.adapter = viewPagerAdapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.add_my_music)
                1 -> tab.text = getString(R.string.lobby_s_playlists)
            }
        }.attach()

        ibSearch.setOnClickListener { listeners.forEach { it.onSearchIconClicked() } }
    }
}