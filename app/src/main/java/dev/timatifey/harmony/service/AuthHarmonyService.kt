package dev.timatifey.harmony.service

import dev.timatifey.harmony.api.harmony.HarmonyAPI
import dev.timatifey.harmony.api.harmony.dto.HarmonyAuthResponseDto
import dev.timatifey.harmony.api.spotify.SpotifyAuthAPI
import dev.timatifey.harmony.common.app.Config.Companion.SPOTIFY_CLIENT_ID
import dev.timatifey.harmony.common.app.Config.Companion.SPOTIFY_REDIRECT_URI
import dev.timatifey.harmony.data.Resource
import dev.timatifey.harmony.data.Status
import dev.timatifey.harmony.data.mappers.mapToResourceSpotifyTokens
import dev.timatifey.harmony.data.mappers.mapToResourceToken
import dev.timatifey.harmony.data.model.harmony.Token
import dev.timatifey.harmony.data.model.spotify.SpotifyTokens
import dev.timatifey.harmony.repo.user.UserRepo
import dev.timatifey.harmony.util.randomString
import kotlinx.coroutines.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthHarmonyService @Inject constructor(
    private val userRepo: UserRepo,
    private val harmonyApi: HarmonyAPI,
    private val ioDispatcher: CoroutineDispatcher,
) {

    // HARMONY
    suspend fun authHarmony(username: String, password: String): Resource<Token> =
        withContext(ioDispatcher) {
            try {
                val authDto: HarmonyAuthResponseDto = harmonyApi.authUser(username, password)
                val token = authDto.mapToResourceToken()
                if (token.status is Status.Success) {
                    userRepo.saveHarmonyTokenToCache(token.data!!)
                }
                return@withContext token
            } catch (t: Throwable) {
                return@withContext Resource.error(msg = t.message)
            }
        }

    suspend fun registerHarmony(
        username: String,
        email: String,
        password: String
    ): Resource<Token> =
        withContext(ioDispatcher) {
            try {
                val authDto: HarmonyAuthResponseDto = harmonyApi.registerUser(
                    login = username,
                    email = email,
                    password = password
                )
                val token = authDto.mapToResourceToken()
                if (token.status is Status.Success) {
                    userRepo.saveHarmonyTokenToCache(token.data!!)
                }
                return@withContext token
            } catch (t: Throwable) {
                return@withContext Resource.error(msg = t.message)
            }
        }

    suspend fun authCacheIsValid(): Resource<Token> =
        withContext(ioDispatcher) {
            try {
                val cacheToken = userRepo.getHarmonyTokenFromCache()
                if (cacheToken.status is Status.Success) {
                    val isValidToken = harmonyApi.isTokenValidate(cacheToken.data!!)
                    return@withContext isValidToken.mapToResourceToken()
                }
                return@withContext Resource.error(cacheToken.message.messageId)
            } catch (t: Throwable) {
                return@withContext Resource.error(t.message)
            }
        }

    suspend fun recoveryAcc(email: String): Resource<Token> =
        withContext(ioDispatcher) {
            try {
                delay(1000)
                //TODO("Recovery account")
                return@withContext Resource.success(Token(randomString(50, 60)))
            } catch (t: Throwable) {
                return@withContext Resource.error(msg = t.message)
            }
        }

    fun logoutHarmony() {
        userRepo.clearAll()
    }

}