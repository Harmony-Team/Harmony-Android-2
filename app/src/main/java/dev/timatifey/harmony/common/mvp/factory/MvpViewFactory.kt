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
import dev.timatifey.harmony.screen.home.group.add.AddGroupMvpView
import dev.timatifey.harmony.screen.home.group.add.AddGroupMvpViewImpl
import dev.timatifey.harmony.screen.home.group.list.GroupListAdapter
import dev.timatifey.harmony.screen.home.group.list.GroupListMvpView
import dev.timatifey.harmony.screen.home.group.list.GroupListMvpViewImpl
import dev.timatifey.harmony.screen.home.group.list.row.GroupListRowMvpView
import dev.timatifey.harmony.screen.home.group.list.row.GroupListRowMvpViewImpl
import dev.timatifey.harmony.screen.home.group.join.JoinGroupMvpView
import dev.timatifey.harmony.screen.home.group.join.JoinGroupMvpViewImpl
import dev.timatifey.harmony.screen.home.lobby.LobbyAdapter
import dev.timatifey.harmony.screen.home.lobby.LobbyMvpView
import dev.timatifey.harmony.screen.home.lobby.LobbyMvpViewImpl
import dev.timatifey.harmony.screen.home.lobby.row.LobbyRowMvpView
import dev.timatifey.harmony.screen.home.lobby.row.LobbyRowMvpViewImpl
import dev.timatifey.harmony.screen.home.group.create.NewGroupMvpView
import dev.timatifey.harmony.screen.home.group.create.NewGroupMvpViewImpl
import dev.timatifey.harmony.screen.home.group.share.ShareGroupMvpView
import dev.timatifey.harmony.screen.home.group.share.ShareGroupMvpViewImpl
import dev.timatifey.harmony.screen.home.lobby.tabs.LobbyTabsMvpView
import dev.timatifey.harmony.screen.home.lobby.tabs.LobbyTabsMvpViewImpl
import dev.timatifey.harmony.screen.home.lobby.tabs.ViewPagerAdapter
import dev.timatifey.harmony.screen.home.lobby.tabs.tracks.TracksMvpView
import dev.timatifey.harmony.screen.home.lobby.tabs.tracks.TracksMvpViewImpl
import dev.timatifey.harmony.screen.home.lobby.tabs.playlists.PlaylistsMvpView
import dev.timatifey.harmony.screen.home.lobby.tabs.playlists.PlaylistsMvpViewImpl
import dev.timatifey.harmony.screen.home.lobby.tabs.tracks.TracksAdapter
import dev.timatifey.harmony.screen.home.lobby.tabs.tracks.row.TracksRowMvpView
import dev.timatifey.harmony.screen.home.lobby.tabs.tracks.row.TracksRowMvpViewImpl
import dev.timatifey.harmony.screen.home.lobby.waiting.WaitingPlaylistMvpView
import dev.timatifey.harmony.screen.home.lobby.waiting.WaitingPlaylistMvpViewImpl
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

    fun createShareGroupMvpView(parent: ViewGroup?): ShareGroupMvpView =
        ShareGroupMvpViewImpl(layoutInflater, parent)

    fun createLobbyRowView(
        parent: ViewGroup,
        listener: LobbyRowMvpView.Listener,
        context: Context
    ): LobbyRowMvpView =
        LobbyRowMvpViewImpl(layoutInflater, parent, listener, Glide.with(context))

    fun createLobbyAdapter(listener: LobbyRowMvpView.Listener, context: Context): LobbyAdapter =
        LobbyAdapter(listener, this, context)

    fun createLobbyMvpView(parent: ViewGroup?): LobbyMvpView =
        LobbyMvpViewImpl(layoutInflater, parent, this)

    fun createLobbyTabsMvpView(
        container: ViewGroup?,
        viewPagerAdapter: ViewPagerAdapter
    ): LobbyTabsMvpView =
        LobbyTabsMvpViewImpl(layoutInflater, container, viewPagerAdapter)

    fun createWaitingPlaylistMvpView(container: ViewGroup?): WaitingPlaylistMvpView =
        WaitingPlaylistMvpViewImpl(layoutInflater, container)

    fun createTracksMvpView(container: ViewGroup?): TracksMvpView =
        TracksMvpViewImpl(layoutInflater, container, this)

    fun createPlaylistsMvpView(container: ViewGroup?): PlaylistsMvpView =
        PlaylistsMvpViewImpl(layoutInflater, container)

    fun createTrackRowMvpView(
        parent: ViewGroup,
        listener: TracksRowMvpView.Listener,
        context: Context
    ): TracksRowMvpView =
        TracksRowMvpViewImpl(layoutInflater, parent, listener, Glide.with(context))

    fun createTracksAdapter(listener: TracksRowMvpView.Listener, context: Context): TracksAdapter =
        TracksAdapter(listener, this, context)

}