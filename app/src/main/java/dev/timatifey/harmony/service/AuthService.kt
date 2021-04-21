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
class AuthService @Inject constructor(
    private val userRepo: UserRepo,
    private val harmonyApi: HarmonyAPI,
    private val spotifyAuthAPI: SpotifyAuthAPI,
    private val ioDispatcher: CoroutineDispatcher
) {
    private var spotifyCodeVerifier: String? = null

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
        userRepo.clearHarmonyTokenFromCache()
    }

    // SPOTIFY
    suspend fun exchangesCodeForAccessToken(code: String): Resource<SpotifyTokens> =
        withContext(ioDispatcher) {
            try {
                if (spotifyCodeVerifier == null) {
                    return@withContext Resource.error(msg = "Spotify Code Verifier is null")
                }
                val spotifyAuthResponseDto = spotifyAuthAPI.exchangesCodeForToken(
                    clientId = SPOTIFY_CLIENT_ID,
                    grantType = "authorization_code",
                    redirectURI = SPOTIFY_REDIRECT_URI,
                    code = code,
                    codeVerifier = spotifyCodeVerifier!!
                )
                val spotifyTokens = spotifyAuthResponseDto.mapToResourceSpotifyTokens()
                if (spotifyTokens.status is Status.Success) {
                    userRepo.saveSpotifyToken(spotifyTokens.data!!)
                }
                return@withContext spotifyTokens
            } catch (t: Throwable) {
                return@withContext Resource.error(msg = t.message)
            }
        }

    suspend fun refreshToken(): Resource<SpotifyTokens> =
        withContext(ioDispatcher) {
            try {
                val tokens = userRepo.getSpotifyTokens()
                if (tokens.status is Error) {
                    return@withContext Resource.error("Spotify token is null")
                }
                val refreshToken = tokens.data!!.refreshToken
                val spotifyAuthResponseDto = spotifyAuthAPI.refreshToken(
                    clientId = SPOTIFY_CLIENT_ID,
                    grantType = "refresh_token",
                    refreshToken = refreshToken
                )
                return@withContext spotifyAuthResponseDto.mapToResourceSpotifyTokens()
            } catch (t: Throwable) {
                return@withContext Resource.error(msg = t.message)
            }
        }

}