package dev.timatifey.harmony.screen.home.groups.lobby.row

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.bumptech.glide.RequestManager
import dev.timatifey.harmony.R
import dev.timatifey.harmony.common.mvp.MvpViewBase
import dev.timatifey.harmony.data.model.harmony.AddUserBtn
import dev.timatifey.harmony.data.model.harmony.HarmonyGroupUser
import dev.timatifey.harmony.data.model.harmony.HarmonyLobbyItem

class LobbyRowMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?,
    private val listener: LobbyRowMvpView.Listener,
    private val glide: RequestManager
) : MvpViewBase(), LobbyRowMvpView {

    override var rootView: View =
        layoutInflater.inflate(R.layout.users_of_group__item, parent, false)
    private val ivCircle: AppCompatImageView = findViewById(R.id.users_of_group__item__circle)
    private val ivImage: AppCompatImageView = findViewById(R.id.users_of_group__item__image)
    private val tvName: AppCompatTextView = findViewById(R.id.users_of_group__item__name)
    private val ivHostStar: AppCompatImageView =
        findViewById(R.id.users_of_group__item__host_image)

    private val colorBtn = getColorStateList(R.color.black_blue)
    private val colorWhite = getColorStateList(R.color.white)
    private val colorPink = getColorStateList(R.color.pink)

    private lateinit var item: HarmonyLobbyItem

    init {
        rootView.setOnClickListener { listener.onItemClicked(item) }
    }

    override fun bindData(item: HarmonyLobbyItem) {
        this.item = item
        when (item) {
            is AddUserBtn -> {
                ivCircle.backgroundTintList = colorBtn
                ivImage.setImageResource(R.drawable.add_user_circle_button)
                tvName.text = getString(R.string.add_a_user)
                ivHostStar.visibility = View.INVISIBLE
                rootView.setPaddingRelative(60, 0, 20, 0)
            }
            is HarmonyGroupUser -> {
                ivCircle.backgroundTintList =
                    if (item.isReady) colorPink else colorWhite
                ivHostStar.visibility =
                    if (item.isHost) View.VISIBLE else View.INVISIBLE

                if (item.avatarUrl == null) {
                    ivImage.setImageResource(R.drawable.avatar)
                } else {
                    glide.load(item.avatarUrl).into(ivImage)
                }
                rootView.setPaddingRelative(20, 0, 20, 0)
                tvName.text = item.login
            }
        }
    }

}