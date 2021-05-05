package dev.timatifey.harmony.lobby

import dev.timatifey.harmony.data.Status
import dev.timatifey.harmony.service.LobbyService
import kotlinx.coroutines.runBlocking

class LobbyStateMachine constructor(
    private val lobbyService: LobbyService,
    private val groupId: Long,
) {

    lateinit var state: LobbyState

    init {
        runBlocking {
            val stateResource = lobbyService.getLobbyStateByGroupId(groupId)
            if (stateResource.status is Status.Success) {
                state = stateResource.data!!
            }
        }
    }

}

sealed class LobbyState {
    object Unready : LobbyState()
    object Waiting : LobbyState()
}