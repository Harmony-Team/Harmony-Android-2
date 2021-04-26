package dev.timatifey.harmony.common.mvp.factory

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import dev.timatifey.harmony.di.scope.ActivityScope
import dev.timatifey.harmony.screen.activity.MainMvpView
import dev.timatifey.harmony.screen.activity.MainMvpViewImpl
import dev.timatifey.harmony.screen.auth.recovery.RecoveryMvpView
import dev.timatifey.harmony.screen.auth.recovery.RecoveryMvpViewImpl
import dev.timatifey.harmony.screen.auth.signin.SignInMvpView
import dev.timatifey.harmony.screen.auth.signin.SignInMvpViewImpl
import dev.timatifey.harmony.screen.auth.signup.SignUpMvpView
import dev.timatifey.harmony.screen.auth.signup.SignUpMvpViewImpl
import dev.timatifey.harmony.screen.auth.spotify.SpotifyAuthMvpView
import dev.timatifey.harmony.screen.auth.spotify.SpotifyAuthMvpViewImpl
import dev.timatifey.harmony.screen.home.groups.addgroup.AddGroupMvpView
import dev.timatifey.harmony.screen.home.groups.addgroup.AddGroupMvpViewImpl
import dev.timatifey.harmony.screen.home.groups.grouplist.GroupListAdapter
import dev.timatifey.harmony.screen.home.groups.grouplist.GroupListMvpView
import dev.timatifey.harmony.screen.home.groups.grouplist.GroupListMvpViewImpl
import dev.timatifey.harmony.screen.home.groups.grouplist.row.GroupListRowMvpView
import dev.timatifey.harmony.screen.home.groups.grouplist.row.GroupListRowMvpViewImpl
import dev.timatifey.harmony.screen.home.groups.joingroup.JoinGroupMvpView
import dev.timatifey.harmony.screen.home.groups.joingroup.JoinGroupMvpViewImpl
import dev.timatifey.harmony.screen.home.groups.newgroup.NewGroupMvpView
import dev.timatifey.harmony.screen.home.groups.newgroup.NewGroupMvpViewImpl
import dev.timatifey.harmony.screen.home.profile.ProfileMvpView
import dev.timatifey.harmony.screen.home.profile.ProfileMvpViewImpl
import dev.timatifey.harmony.screen.home.settings.SettingsMvpView
import dev.timatifey.harmony.screen.home.settings.SettingsMvpViewImpl
import javax.inject.Inject

@ActivityScope
class MvpViewFactory @Inject constructor(
    private val layoutInflater: LayoutInflater,
) {

    fun createSignInMvpView(parent: ViewGroup?): SignInMvpView =
        SignInMvpViewImpl(layoutInflater, parent)

    fun createSignUpMvpView(parent: ViewGroup?): SignUpMvpView =
        SignUpMvpViewImpl(layoutInflater, parent)

    fun createRecoveryMvpView(parent: ViewGroup?): RecoveryMvpView =
        RecoveryMvpViewImpl(layoutInflater, parent)

    fun createSpotifyAuthMvpView(parent: ViewGroup?): SpotifyAuthMvpView =
        SpotifyAuthMvpViewImpl(layoutInflater, parent)

    fun createMainMvpView(): MainMvpView =
        MainMvpViewImpl(layoutInflater)

    fun createProfileMvpView(parent: ViewGroup?): ProfileMvpView =
        ProfileMvpViewImpl(layoutInflater, parent)

    fun createSettingsMvpView(parent: ViewGroup?): SettingsMvpView =
        SettingsMvpViewImpl(layoutInflater, parent)

    fun createGroupListMvpView(parent: ViewGroup?): GroupListMvpView =
        GroupListMvpViewImpl(layoutInflater, parent, this)

    fun createGroupListRowView(
        parent: ViewGroup?,
        listener: GroupListRowMvpView.Listener,
        context: Context,
    ): GroupListRowMvpView =
        GroupListRowMvpViewImpl(layoutInflater, parent, listener, Glide.with(context))

    fun createGroupListAdapter(
        listener: GroupListRowMvpView.Listener,
        context: Context
    ): GroupListAdapter =
        GroupListAdapter(listener, this, context)

    fun createAddGroupMvpView(parent: ViewGroup?): AddGroupMvpView =
        AddGroupMvpViewImpl(layoutInflater, parent)

    fun createNewGroupMvpView(parent: ViewGroup?): NewGroupMvpView =
        NewGroupMvpViewImpl(layoutInflater, parent)

    fun createJoinGroupMvpView(parent: ViewGroup?): JoinGroupMvpView =
        JoinGroupMvpViewImpl(layoutInflater, parent)
}