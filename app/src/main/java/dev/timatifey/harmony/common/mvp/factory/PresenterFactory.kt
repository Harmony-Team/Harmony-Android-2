package dev.timatifey.harmony.common.mvp.factory

import dev.timatifey.harmony.screen.auth.signin.SignInPresenter
import dev.timatifey.harmony.common.nav.BackPressDispatcher
import dev.timatifey.harmony.common.nav.AppScreenNavigator
import dev.timatifey.harmony.di.scope.ActivityScope
import dev.timatifey.harmony.screen.activity.MainPresenter
import dev.timatifey.harmony.screen.auth.recovery.RecoveryPresenter
import dev.timatifey.harmony.screen.auth.signup.SignUpPresenter
import dev.timatifey.harmony.screen.auth.spotify.SpotifyAuthPresenter
import dev.timatifey.harmony.screen.home.groups.addgroup.AddGroupPresenter
import dev.timatifey.harmony.screen.home.groups.grouplist.GroupListPresenter
import dev.timatifey.harmony.screen.home.groups.joingroup.JoinGroupPresenter
import dev.timatifey.harmony.screen.home.groups.newgroup.NewGroupPresenter
import dev.timatifey.harmony.screen.home.groups.newgroup.PickPhotoIntentListener
import dev.timatifey.harmony.screen.home.groups.sharegroup.ShareIntentListener
import dev.timatifey.harmony.screen.home.groups.sharegroup.ShareGroupPresenter
import dev.timatifey.harmony.screen.home.profile.ProfilePresenter
import dev.timatifey.harmony.screen.home.settings.SettingsPresenter
import dev.timatifey.harmony.service.AuthHarmonyService
import dev.timatifey.harmony.service.AuthSpotifyService
import dev.timatifey.harmony.service.GroupService
import dev.timatifey.harmony.service.UserService
import javax.inject.Inject

@ActivityScope
class PresenterFactory @Inject constructor(
    private val appScreenNavigator: AppScreenNavigator,
    private val backPressDispatcher: BackPressDispatcher,
    private val authHarmonyService: AuthHarmonyService,
    private val authSpotifyService: AuthSpotifyService,
    private val userService: UserService,
    private val groupService: GroupService,
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
            userService = userService,
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
        shareLink: String
    ): ShareGroupPresenter =
        ShareGroupPresenter(
            backPressDispatcher = backPressDispatcher,
            appScreenNavigator = appScreenNavigator,
            shareIntentListener = listenerShare,
            shareLink = shareLink,
        )

    fun createJoinGroupPresenter(): JoinGroupPresenter =
        JoinGroupPresenter(
            backPressDispatcher = backPressDispatcher,
            appScreenNavigator = appScreenNavigator,
            groupService = groupService
        )
}