package dev.timatifey.harmony.service

import dev.timatifey.harmony.api.spotify.SpotifyAuthAPI
import dev.timatifey.harmony.app.Config.Companion.SPOTIFY_CLIENT_ID
import dev.timatifey.harmony.app.Config.Companion.SPOTIFY_REDIRECT_URI
import dev.timatifey.harmony.data.Resource
import dev.timatifey.harmony.data.Status
import dev.timatifey.harmony.data.mappers.toResourceSpotifyTokens
import dev.timatifey.harmony.data.model.harmony.Token
import dev.timatifey.harmony.data.model.spotify.SpotifyTokens
import dev.timatifey.harmony.repo.user.UserRepo
import kotlinx.coroutines.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthSpotifyService @Inject constructor(
    private val userRepo: UserRepo,
    private val spotifyAuthAPI: SpotifyAuthAPI,
    private val ioDispatcher: CoroutineDispatcher
) {

    suspend fun exchangesCodeForAccessToken(
        code: String,
        spotifyCodeVerifier: String
    ): Resource<SpotifyTokens> =
        withContext(ioDispatcher) {
            try {
                val spotifyAuthResponseDto = spotifyAuthAPI.exchangesCodeForToken(
                    clientId = SPOTIFY_CLIENT_ID,
                    grantType = "authorization_code",
                    redirectURI = SPOTIFY_REDIRECT_URI,
                    code = code,
                    codeVerifier = spotifyCodeVerifier
                )
                val spotifyTokens = spotifyAuthResponseDto.toResourceSpotifyTokens()
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
                return@withContext refreshToken(tokens.data!!.refreshToken)
            } catch (t: Throwable) {
                return@withContext Resource.error(msg = t.message)
            }
        }

    private suspend fun refreshToken(refreshToken: Token): Resource<SpotifyTokens> =
        withContext(ioDispatcher) {
            try {
                val spotifyAuthResponseDto = spotifyAuthAPI.refreshToken(
                    clientId = SPOTIFY_CLIENT_ID,
                    grantType = "refresh_token",
                    refreshToken = refreshToken
                )
                val spotifyTokens = spotifyAuthResponseDto.toResourceSpotifyTokens()
                if (spotifyTokens.status is Status.Success) {
                    userRepo.saveSpotifyToken(spotifyTokens.data!!)
                }
                return@withContext spotifyTokens
            } catch (t: Throwable) {
                if (t.message?.contains("400") == true) {
                    return@withContext Resource.error(msg = "Refresh token revoked")
                }
                return@withContext Resource.error(msg = t.message)
            }
        }

}