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
import dev.timatifey.harmony.screen.home.groups.newgroup.NewGroupPresenter
import dev.timatifey.harmony.screen.home.profile.ProfilePresenter
import dev.timatifey.harmony.screen.home.settings.SettingsPresenter
import dev.timatifey.harmony.service.AuthHarmonyService
import dev.timatifey.harmony.service.AuthSpotifyService
import dev.timatifey.harmony.service.GroupListService
import dev.timatifey.harmony.service.UserService
import javax.inject.Inject

@ActivityScope
class PresenterFactory @Inject constructor(
    private val appScreenNavigator: AppScreenNavigator,
    private val backPressDispatcher: BackPressDispatcher,
    private val authHarmonyService: AuthHarmonyService,
    private val authSpotifyService: AuthSpotifyService,
    private val userService: UserService,
    private val groupListService: GroupListService,
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
            groupListService = groupListService,
        )

    fun createAddGroupPresenter(): AddGroupPresenter =
        AddGroupPresenter(
            backPressDispatcher = backPressDispatcher,
            appScreenNavigator = appScreenNavigator,
        )

    fun createNewGroupPresenter(): NewGroupPresenter =
        NewGroupPresenter(
            backPressDispatcher = backPressDispatcher,
            appScreenNavigator = appScreenNavigator,
            groupListService = groupListService,
        )
}