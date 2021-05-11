package dev.timatifey.harmony.common.mvp.factory

import dev.timatifey.harmony.screen.auth.signin.SignInPresenter
import dev.timatifey.harmony.common.nav.BackPressDispatcher
import dev.timatifey.harmony.common.nav.app.AppScreenNavigator
import dev.timatifey.harmony.common.nav.lobby.LobbyFragmentNavigator
import dev.timatifey.harmony.di.scope.ActivityScope
import dev.timatifey.harmony.repo.lobby.LobbyProvider
import dev.timatifey.harmony.screen.activity.MainPresenter
import dev.timatifey.harmony.screen.auth.recovery.RecoveryPresenter
import dev.timatifey.harmony.screen.auth.signup.SignUpPresenter
import dev.timatifey.harmony.screen.auth.spotify.SpotifyAuthPresenter
import dev.timatifey.harmony.screen.home.group.add.AddGroupPresenter
import dev.timatifey.harmony.screen.home.group.list.GroupListPresenter
import dev.timatifey.harmony.screen.home.group.join.JoinGroupPresenter
import dev.timatifey.harmony.screen.home.lobby.LobbyPresenter
import dev.timatifey.harmony.screen.home.group.create.NewGroupPresenter
import dev.timatifey.harmony.screen.home.group.create.PickPhotoIntentListener
import dev.timatifey.harmony.screen.home.group.share.ShareIntentListener
import dev.timatifey.harmony.screen.home.group.share.ShareGroupPresenter
import dev.timatifey.harmony.screen.home.lobby.tabs.LobbyTabsPageController
import dev.timatifey.harmony.screen.home.lobby.tabs.LobbyTabsPresenter
import dev.timatifey.harmony.screen.home.lobby.tabs.tracks.TracksPresenter
import dev.timatifey.harmony.screen.home.lobby.tabs.playlists.PlaylistsPresenter
import dev.timatifey.harmony.screen.home.lobby.waiting.WaitingPlaylistPresenter
import dev.timatifey.harmony.screen.home.profile.ProfilePresenter
import dev.timatifey.harmony.screen.home.settings.SettingsPresenter
import dev.timatifey.harmony.service.*
import javax.inject.Inject

@ActivityScope
class PresenterFactory @Inject constructor(
    private val appScreenNavigator: AppScreenNavigator,
    private val backPressDispatcher: BackPressDispatcher,
    private val authHarmonyService: AuthHarmonyService,
    private val authSpotifyService: AuthSpotifyService,
    private val lobbyProvider: LobbyProvider,
    private val userService: UserService,
    private val groupService: GroupService,
    private val lobbyService: LobbyService,
    private val tracksService: TracksService,
) {

    fun createSignInPresenter(): SignInPresenter =
        SignInPresenter(
            appScreenNavigator = appScreenNavigator,
            authHarmonyService = authHarmonyService,
            backPressDispatcher = backPressDispatcher
        )

    fun createSignUpPresenter(): SignUpPresenter =
        SignUpPresenter(
            appScreenNavigator = appScreenNavigator,
            authHarmonyService = authHarmonyService,
            backPressDispatcher = backPressDispatcher
        )

    fun createRecoveryPresenter(): RecoveryPresenter =
        RecoveryPresenter(
            appScreenNavigator = appScreenNavigator,
            authHarmonyService = authHarmonyService,
            backPressDispatcher = backPressDispatcher
        )

    fun createSpotifyAuthPresenter(): SpotifyAuthPresenter =
        SpotifyAuthPresenter(
            appScreenNavigator = appScreenNavigator,
            backPressDispatcher = backPressDispatcher,
            authSpotifyService = authSpotifyService,
            userService = userService,
        )

    fun createMainPresenter(): MainPresenter =
        MainPresenter(
            appScreenNavigator = appScreenNavigator,
            authHarmonyService = authHarmonyService,
            backPressDispatcher = backPressDispatcher,
        )

    fun createProfilePresenter(): ProfilePresenter =
        ProfilePresenter(
            userService = userService,
            backPressDispatcher = backPressDispatcher,
        )

    fun createSettingsPresenter(): SettingsPresenter =
        SettingsPresenter(
            appScreenNavigator = appScreenNavigator,
            backPressDispatcher = backPressDispatcher,
            userService = userService,
        )

    fun createGroupListPresenter(): GroupListPresenter =
        GroupListPresenter(
            appScreenNavigator = appScreenNavigator,
            backPressDispatcher = backPressDispatcher,
            groupService = groupService,
        )

    fun createAddGroupPresenter(): AddGroupPresenter =
        AddGroupPresenter(
            backPressDispatcher = backPressDispatcher,
            appScreenNavigator = appScreenNavigator,
        )

    fun createNewGroupPresenter(listener: PickPhotoIntentListener): NewGroupPresenter =
        NewGroupPresenter(
            backPressDispatcher = backPressDispatcher,
            appScreenNavigator = appScreenNavigator,
            groupService = groupService,
            pickPhotoIntentListener = listener,
        )

    fun createShareGroupPresenter(
        listenerShare: ShareIntentListener,
        shareLink: String,
        groupId: Long,
    ): ShareGroupPresenter =
        ShareGroupPresenter(
            backPressDispatcher = backPressDispatcher,
            appScreenNavigator = appScreenNavigator,
            shareIntentListener = listenerShare,
            shareCode = shareLink,
            groupId = groupId,
        )

    fun createJoinGroupPresenter(): JoinGroupPresenter =
        JoinGroupPresenter(
            backPressDispatcher = backPressDispatcher,
            appScreenNavigator = appScreenNavigator,
            groupService = groupService
        )

    fun createLobbyPresenter(listenerShare: ShareIntentListener): LobbyPresenter =
        LobbyPresenter(
            lobbyProvider = lobbyProvider,
            groupService = groupService,
            lobbyService = lobbyService,
            backPressDispatcher = backPressDispatcher,
            appScreenNavigator = appScreenNavigator,
            listenerShare = listenerShare,
        )

    fun createWaitingPlaylistPresenter(lobbyFragmentNavigator: LobbyFragmentNavigator): WaitingPlaylistPresenter =
        WaitingPlaylistPresenter(
            lobbyFragmentNavigator = lobbyFragmentNavigator,
            lobbyService = lobbyService,
        )

    fun createLobbyTabsPresenter(lobbyFragmentNavigator: LobbyFragmentNavigator): LobbyTabsPresenter =
        LobbyTabsPresenter(
            userService = userService,
            lobbyFragmentNavigator = lobbyFragmentNavigator,
            lobbyService = lobbyService,
        )

    fun createTracksPresenter(
        pageController: LobbyTabsPageController,
        lobbyFragmentNavigator: LobbyFragmentNavigator
    ): TracksPresenter =
        TracksPresenter(
            lobbyProvider = lobbyProvider,
            lobbyService = lobbyService,
            pageController = pageController,
            lobbyFragmentNavigator = lobbyFragmentNavigator,
            tracksService = tracksService,
        )

    fun createPlaylistsPresenter(lobbyFragmentNavigator: LobbyFragmentNavigator): PlaylistsPresenter =
        PlaylistsPresenter(
            lobbyFragmentNavigator = lobbyFragmentNavigator,
            userService = userService,
            lobbyService = lobbyService,
            appScreenNavigator = appScreenNavigator,
            lobbyProvider = lobbyProvider,
        )
}