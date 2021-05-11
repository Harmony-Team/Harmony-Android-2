package dev.timatifey.harmony.repo.lobby

import dev.timatifey.harmony.data.model.harmony.HarmonyGroupUser
import dev.timatifey.harmony.data.model.harmony.User
import dev.timatifey.harmony.service.LobbyService
import dev.timatifey.harmony.service.UserService
import kotlinx.coroutines.runBlocking
import java.lang.NullPointerException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LobbyProvider @Inject constructor(
    private val lobbyService: LobbyService,
    private val userService: UserService,
) {
    var groupId: Long? = null
        private set

    var state: LobbyState? = null
    val users = mutableListOf<HarmonyGroupUser>()
    var hostLogin: String? = null
        private set
    var user: User? = null
        private set

    fun setGroupId(groupId: Long) {
        this.groupId = groupId
        initState()
        initUser()
    }

    private fun initState() {
        if (groupId == null) {
            throw NullPointerException("Group Id is null")
        }
        runBlocking {
            state = lobbyService.getLobbyStateByGroupId(groupId!!).data!!
        }
    }

    private fun initUser() {
        if (groupId == null) {
            throw NullPointerException("Group Id is null")
        }
        runBlocking {
            user = userService.getUser().data!!
            hostLogin = lobbyService.getHostLobbyByGroupId(groupId!!).data!!
        }
    }

}

sealed class LobbyState {
    object Unready : LobbyState()
    object Waiting : LobbyState()
}