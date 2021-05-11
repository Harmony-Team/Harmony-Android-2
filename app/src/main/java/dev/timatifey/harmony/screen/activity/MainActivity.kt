package dev.timatifey.harmony.screen.activity

import android.os.Bundle
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.yarolegovich.slidingrootnav.SlideGravity
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder
import dev.timatifey.harmony.R
import dev.timatifey.harmony.common.base.BaseActivity

class MainActivity : BaseActivity() {

    private lateinit var presenter: MainPresenter
    private lateinit var view: MainMvpView
    lateinit var menuController: MenuController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Harmony)
        setContentView(R.layout.activity_main)

        val slidingRootNav = SlidingRootNavBuilder(this)
            .withMenuLocked(appScreenNavigator.appSettings.isAuthorized.not())
            .withMenuOpened(false)
            .withContentClickableWhenMenuOpened(false)
            .withSavedState(savedInstanceState)
            .withMenuLayout(R.layout.menu_left_drawer)
            .inject()

        view = mvpViewFactory.createMainMvpView(slidingRootNav)
        menuController = view

        presenter = presenterFactory.createMainPresenter()
        presenter.bindView(view)
        presenter.bindMenuController(menuController)
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

}