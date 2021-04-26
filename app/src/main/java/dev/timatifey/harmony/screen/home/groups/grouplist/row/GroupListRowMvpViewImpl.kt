package dev.timatifey.harmony.screen.home.groups.grouplist.row

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.bumptech.glide.RequestManager
import dev.timatifey.harmony.R
import dev.timatifey.harmony.common.mvp.MvpViewBase
import dev.timatifey.harmony.data.model.harmony.HarmonyGroup

class GroupListRowMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?,
    private val listener: GroupListRowMvpView.Listener,
    private val glide: RequestManager
) : MvpViewBase(), GroupListRowMvpView {

    override var rootView: View = layoutInflater.inflate(R.layout.group_list__item, parent, false)
    private val ivGroupImage: AppCompatImageView = findViewById(R.id.group_list__item__image)
    private val tvGroupName: AppCompatTextView = findViewById(R.id.group_list__item__group_name)
    private val tvGroupDate: AppCompatTextView =
        findViewById(R.id.group_list__item__group_date_created)

    private lateinit var group: HarmonyGroup

    init {
        rootView.setOnClickListener { listener.onGroupClicked(group) }
    }

    override fun bindData(group: HarmonyGroup) {
        this.group = group

        tvGroupName.text = group.name
        tvGroupDate.text = group.dateCreated
        if (group.avatarUrl == null) {
            ivGroupImage.setImageResource(R.drawable.avatar)
        } else {
            glide.load(group.avatarUrl).into(ivGroupImage)
        }
    }
}