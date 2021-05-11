package dev.timatifey.harmony.service

import dev.timatifey.harmony.R
import dev.timatifey.harmony.api.harmony.HarmonyAPI
import dev.timatifey.harmony.data.Resource
import dev.timatifey.harmony.data.Status
import dev.timatifey.harmony.data.mappers.toResourceBoolean
import dev.timatifey.harmony.repo.lobby.LobbyState
import dev.timatifey.harmony.repo.user.UserRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LobbyService @Inject constructor(
    private val groupService: GroupService,
    private val harmonyAPI: HarmonyAPI,
    private val ioDispatcher: CoroutineDispatcher,
    private val userRepo: UserRepo,
) {

    suspend fun getLobbyStateByGroupId(groupId: Long): Resource<LobbyState> =
        withContext(ioDispatcher) {
            Resource.success(LobbyState.Unready)
        }

    suspend fun getHostLobbyByGroupId(groupId: Long): Resource<String> =
        withContext(ioDispatcher) {
            try {
                val groupRes = groupService.getGroupById(groupId)
                if (groupRes.status is Status.Success) {
                    return@withContext Resource.success(groupRes.data!!.hostLogin)
                }
                return@withContext Resource.error(msg = groupRes.message.message)
            } catch (t: Throwable) {
                return@withContext Resource.error(msg = t.message)
            }

        }

    suspend fun sendState(lobbyState: LobbyState): Resource<Boolean> =
        withContext(ioDispatcher) {
            Resource.success(true)
        }

    suspend fun generateShareCode(groupId: Long): Resource<String> =
        withContext(ioDispatcher) {
            try {
                val harmonyToken = userRepo.getHarmonyTokenFromCache().data
                    ?: return@withContext Resource.error(msgId = R.string.token_does_not_exist)
                val share = harmonyAPI.createInviteCode(harmonyToken, groupId, 10)
                if (share.code == 0) {
                    return@withContext Resource.success(share.message!!)
                }
                return@withContext Resource.error(msg = share.message)
            } catch (t: Throwable) {
                return@withContext Resource.error(msg = t.message)
            }
        }

    suspend fun addTrack(trackId: Long, groupId: Long): Resource<Boolean> =
        withContext(ioDispatcher) {
            try {
                val harmonyToken = userRepo.getHarmonyTokenFromCache().data
                    ?: return@withContext Resource.error(msgId = R.string.token_does_not_exist)
                return@withContext harmonyAPI.addSong(harmonyToken, groupId, trackId)
                    .toResourceBoolean()
            } catch (t: Throwable) {
                return@withContext Resource.error(msg = t.message)
            }
        }

    suspend fun removeTrack(trackId: Long, groupId: Long): Resource<Boolean> =
        withContext(ioDispatcher) {
            try {
                val harmonyToken = userRepo.getHarmonyTokenFromCache().data
                    ?: return@withContext Resource.error(msgId = R.string.token_does_not_exist)
                return@withContext harmonyAPI.addSong(harmonyToken, groupId, trackId)
                    .toResourceBoolean()
            } catch (t: Throwable) {
                return@withContext Resource.error(msg = t.message)
            }
        }
}