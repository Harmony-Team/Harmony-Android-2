package dev.timatifey.harmony.service

import dev.timatifey.harmony.api.harmony.HarmonyAPI
import dev.timatifey.harmony.api.harmony.dto.HarmonyAuthResponseDto
import dev.timatifey.harmony.api.spotify.SpotifyAuthAPI
import dev.timatifey.harmony.data.Resource
import dev.timatifey.harmony.data.ResponseHandler
import dev.timatifey.harmony.data.mappers.mapToResourceToken
import dev.timatifey.harmony.data.model.harmony.Token
import dev.timatifey.harmony.repo.user.UserRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthService @Inject constructor(
    private val userRepo: UserRepo,
    private val harmonyApi: HarmonyAPI,
    private val spotifyAuthAPI: SpotifyAuthAPI,
    private val ioDispatcher: CoroutineDispatcher
) {

    suspend fun authHarmony(username: String, password: String): Resource<Token> {
        return withContext(ioDispatcher) {
            try {
                val authDto: HarmonyAuthResponseDto = harmonyApi.authUser(username, password)
                return@withContext authDto.mapToResourceToken()
            } catch (t: Throwable) {
                return@withContext Resource.error(msg = t.message)
            }
        }
    }

    suspend fun authCacheIsValid(): Resource<Token> {
        return withContext(ioDispatcher) {
            try {
                //TODO("Local Cache check on auth token valid")
                return@withContext Resource.error(msg="Auth Cache doesn't exist")
            } catch (t: Throwable) {
                return@withContext Resource.error(t.message)
            }
        }
    }
}