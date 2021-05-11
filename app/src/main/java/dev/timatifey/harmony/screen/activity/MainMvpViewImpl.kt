package dev.timatifey.harmony.screen.activity

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yarolegovich.slidingrootnav.SlidingRootNav
import dev.timatifey.harmony.R
import dev.timatifey.harmony.common.mvp.MvpViewObservableBase
import dev.timatifey.harmony.screen.menu.*
import javax.inject.Inject

class MainMvpViewImpl @Inject constructor(
    private val slidingRootNav: SlidingRootNav,
) : MvpViewObservableBase<MainMvpView.Listener>(), MainMvpView, MenuController,
    DrawerAdapter.OnItemSelectedListener {

    companion object {
        const val POS_PROFILE = 0
        const val POS_GROUPS = 1
        const val POS_SETTINGS = 2
        const val POS_LOGOUT = 4

        val screenTitleIds = listOf(
            R.string.profile,
            R.string.groups,
            R.string.settings,
            -1,
            R.string.logout
        )
    }

    override var rootView: View = slidingRootNav.layout.rootView
    private val tvUsername: AppCompatTextView =  findViewById(R.id.navigation__header_name)
    private val drawerList: RecyclerView = findViewById(R.id.navigation__list)

    @Suppress("UNCHECKED_CAST")
    private val drawerAdapter = DrawerAdapter(
        listOf(
            createItemFor(POS_PROFILE).setChecked(true),
            createItemFor(POS_GROUPS),
            createItemFor(POS_SETTINGS),
            SpaceItem(48),
            createItemFor(POS_LOGOUT)
        ) as List<DrawerItem<DrawerAdapter.ViewHolder>>
    )

    init {
        drawerAdapter.setListener(this)

        drawerList.apply {
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(context)
            adapter = drawerAdapter
        }
    }

    override fun closeMenu() {
        slidingRootNav.closeMenu(true)
    }

    override fun setMenuUsername(username: String) {
        tvUsername.text = username
    }

    override fun setSelected(position: Int) {
        drawerAdapter.setSelected(position)
    }

    override fun openMenu() {
        slidingRootNav.openMenu(true)
    }

    override fun unlockMenu() {
        slidingRootNav.isMenuLocked = false
    }

    override fun lockMenu() {
        slidingRootNav.isMenuLocked = true
    }

    override fun drawerIsOpen(): Boolean =
        slidingRootNav.isMenuOpened

    private fun createItemFor(position: Int): DrawerItem<out DrawerAdapter.ViewHolder> {
        return SimpleItem(getString(screenTitleIds[position]))
            .withTextAppearance(R.style.menuItemTextAppearance)
            .withSelectedTextAppearance(R.style.menuItemTextAppearance_Selected)
//            .withTextTint(getColor(R.color.text_main))
//            .withSelectedTextTint(getColor(R.color.white))
    }

    override fun onItemSelected(position: Int) {
        listeners.forEach { it.onItemSelected(position) }
    }

}