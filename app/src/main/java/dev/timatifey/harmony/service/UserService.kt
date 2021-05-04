package dev.timatifey.harmony.service

import android.util.Log
import dev.timatifey.harmony.R
import dev.timatifey.harmony.api.harmony.HarmonyAPI
import dev.timatifey.harmony.api.harmony.dto.HarmonyIntegrateSpotifyBodyDto
import dev.timatifey.harmony.api.harmony.dto.HarmonyUserDto
import dev.timatifey.harmony.api.spotify.SpotifyAPI
import dev.timatifey.harmony.data.Resource
import dev.timatifey.harmony.data.Status
import dev.timatifey.harmony.data.mappers.toResourceBoolean
import dev.timatifey.harmony.data.mappers.toResourceSpotifyUserBody
import dev.timatifey.harmony.data.mappers.toResourceUserDto
import dev.timatifey.harmony.data.mappers.toSpotifyTokens
import dev.timatifey.harmony.data.model.harmony.Token
import dev.timatifey.harmony.data.model.harmony.User
import dev.timatifey.harmony.data.model.settings.SettingFields
import dev.timatifey.harmony.data.model.spotify.SpotifyUserBody
import dev.timatifey.harmony.repo.user.UserRepo
import dev.timatifey.harmony.util.randomString
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserService @Inject constructor(
    private val userRepo: UserRepo,
    private val harmonyApi: HarmonyAPI,
    private val spotifyApi: SpotifyAPI,
    private val ioDispatcher: CoroutineDispatcher,
    private val authSpotifyService: AuthSpotifyService,
) {

    suspend fun getUser(): Resource<User> =
        withContext(ioDispatcher) {
            try {
                val user = userRepo.getHarmonyUser()
                if (user.status is Status.Error) {
                    return@withContext fetchUser()
                }
                return@withContext user
            } catch (t: Throwable) {
                return@withContext Resource.error(msg = t.message)
            }
        }

    private suspend fun fetchUser(): Resource<User> =
        withContext(ioDispatcher) {
            try {
                val token = userRepo.getHarmonyTokenFromCache().data
                    ?: return@withContext Resource.error(msgId = R.string.token_does_not_exist)

                val userDtoResource = harmonyApi.getUser(token).toResourceUserDto()
                if (userDtoResource.status is Status.Error) {
                    return@withContext Resource.error(msgId = userDtoResource.message.messageId)
                }

                val userDto: HarmonyUserDto = userDtoResource.data!!
                if (userDto.spotifyHarmony != null) {
                    userRepo.saveSpotifyToken(userDto.spotifyHarmony!!.toSpotifyTokens())
                }

                val user = User(
                    username = userDto.username,
                    email = userDto.email,
                    password = randomString(6, 10), //TODO("fetchUserPassword")
                    harmonyToken = token,
                    spotifyInfo = fetchSpotifyUser().data
                )
                userRepo.saveHarmonyUser(user)
                return@withContext Resource.success(user)
            } catch (t: Throwable) {
                return@withContext Resource.error(msg = t.message)
            }
        }

    fun getUserSettings(): Resource<SettingFields> =
        userRepo.getHarmonyUserSettings()

    private suspend fun fetchSpotifyUser(): Resource<SpotifyUserBody> =
        withContext(ioDispatcher) {
            try {
                val spotifyTokens = userRepo.getSpotifyTokens()
                if (spotifyTokens.status is Status.Error) {
                    Log.e("UserService", "Spotify token is null")
                    return@withContext Resource.error(msg = "Spotify token is null")
                }
                return@withContext fetchSpotifyUser(spotifyTokens.data!!.accessToken)
            } catch (t: Throwable) {
                return@withContext Resource.error(msg = t.message)
            }
        }

    private suspend fun fetchSpotifyUser(spotifyToken: Token): Resource<SpotifyUserBody> =
        withContext(ioDispatcher) {
            try {
                val dto = spotifyApi.getCurrentUser(Token("Bearer ${spotifyToken.value}"))
                return@withContext dto.toResourceSpotifyUserBody()
            } catch (t: Throwable) {
                if (t.message?.contains("401") == true) {
                    val tokens = authSpotifyService.refreshToken()
                    if (tokens.status is Status.Success) {
                        integrateSpotify()
                        return@withContext fetchSpotifyUser(tokens.data!!.accessToken)
                    }
                }
                return@withContext Resource.error(msg = t.message)
            }
        }

    suspend fun integrateSpotify(): Resource<Boolean> =
        withContext(ioDispatcher) {
            try {
                val harmonyToken = userRepo.getHarmonyTokenFromCache().data
                var spotifyId = userRepo.getHarmonyUser().data?.spotifyInfo?.id
                val spotifyTokens = userRepo.getSpotifyTokens().data
                if (spotifyTokens == null || harmonyToken == null) {
                    return@withContext Resource.error(msgId = R.string.token_does_not_exist)
                }
                if (spotifyId == null) {
                    val spotifyBody = fetchSpotifyUser()
                    if (spotifyBody.status is Status.Success) {
                        spotifyId = spotifyBody.data!!.id
                    } else {
                        return@withContext Resource.error(msg = spotifyBody.message.message)
                    }
                }
                return@withContext integrateSpotify(
                    harmonyToken,
                    spotifyId,
                    spotifyTokens.accessToken,
                    spotifyTokens.refreshToken,
                )
            } catch (t: Throwable) {
                return@withContext Resource.error(msg = t.message)
            }
        }

    private suspend fun integrateSpotify(
        harmonyToken: Token,
        spotifyId: String,
        accessToken: Token,
        refreshToken: Token,
    ): Resource<Boolean> =
        withContext(ioDispatcher) {
            try {
                val obj = HarmonyIntegrateSpotifyBodyDto(
                    spotifyId = spotifyId,
                    accessToken = accessToken.value,
                    refreshToken = refreshToken.value
                )
                val response =
                    harmonyApi.integrateSpotify(
                        authToken = harmonyToken,
                        harmonyIntegrateSpotifyBodyDto = obj
                    )
                return@withContext response.toResourceBoolean()
            } catch (t: Throwable) {
                return@withContext Resource.error(msg = t.message)
            }
        }

    suspend fun disintegrateSpotify(): Resource<Boolean> =
        withContext(ioDispatcher) {
            try {
                val harmonyToken = userRepo.getHarmonyTokenFromCache().data
                    ?: return@withContext Resource.error(msgId = R.string.token_does_not_exist)
                return@withContext disintegrateSpotify(harmonyToken)
            } catch (t: Throwable) {
                return@withContext Resource.error(msg = t.message)
            }
        }

    private suspend fun disintegrateSpotify(harmonyToken: Token): Resource<Boolean> =
        withContext(ioDispatcher) {
            try {
                val response = harmonyApi.disintegrateSpotify(authToken = harmonyToken)
                val result = response.toResourceBoolean()
                if (result.status is Status.Success) {
                    userRepo.clearSpotify()
                }
                return@withContext result
            } catch (t: Throwable) {
                return@withContext Resource.error(msg = t.message)
            }
        }
}