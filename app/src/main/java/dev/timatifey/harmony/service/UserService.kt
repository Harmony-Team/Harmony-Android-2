package dev.timatifey.harmony.service

import dev.timatifey.harmony.R
import dev.timatifey.harmony.api.harmony.HarmonyAPI
import dev.timatifey.harmony.api.harmony.dto.HarmonyIntegrateSpotifyBodyDto
import dev.timatifey.harmony.api.harmony.dto.HarmonyUserDto
import dev.timatifey.harmony.api.spotify.SpotifyAPI
import dev.timatifey.harmony.data.Resource
import dev.timatifey.harmony.data.Status
import dev.timatifey.harmony.data.mappers.mapToResourceBoolean
import dev.timatifey.harmony.data.mappers.mapToResourceSpotifyUserBody
import dev.timatifey.harmony.data.mappers.mapToResourceUserDto
import dev.timatifey.harmony.data.mappers.mapToSpotifyTokens
import dev.timatifey.harmony.data.model.harmony.User
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
) {

    suspend fun fetchUser(): Resource<User> =
        withContext(ioDispatcher) {
            try {
                val token = userRepo.getHarmonyTokenFromCache().data
                    ?: return@withContext Resource.error(msgId = R.string.token_does_not_exist)
                val userDtoResource = harmonyApi.getUser(token).mapToResourceUserDto()
                if (userDtoResource.status is Status.Error) {
                    return@withContext Resource.error(msgId = userDtoResource.message.messageId)
                }
                val userDto: HarmonyUserDto = userDtoResource.data!!
                if (userDto.spotifyHarmony != null) {
                    userRepo.saveSpotifyToken(userDto.spotifyHarmony!!.mapToSpotifyTokens())
                }

                return@withContext Resource.success(
                    User(
                        username = userDto.username,
                        email = userDto.email,
                        password = randomString(6, 10), //TODO("fetchUserPassword")
                        harmonyToken = token,
                        spotifyInfo = fetchSpotifyUser().data
                    )
                )
            } catch (t: Throwable) {
                return@withContext Resource.error(msg = t.message)
            }
        }

    suspend fun fetchSpotifyUser(): Resource<SpotifyUserBody> =
        withContext(ioDispatcher) {
            try {
                val spotifyTokens = userRepo.getSpotifyTokens()
                if (spotifyTokens.status is Status.Error) {
                    return@withContext Resource.error(msg = "Spotify token is null")
                }
                val dto = spotifyApi.getCurrentUser(spotifyTokens.data!!.accessToken)
                return@withContext dto.mapToResourceSpotifyUserBody()
            } catch (t: Throwable) {
                return@withContext Resource.error(msg = t.message)
            }
        }

    suspend fun integrateSpotify(): Resource<Boolean> =
        withContext(ioDispatcher) {
            try {
                val harmonyToken = userRepo.getHarmonyTokenFromCache().data
                val spotifyId = userRepo.getHarmonyUser().data?.spotifyInfo?.id
                val spotifyTokens = userRepo.getSpotifyTokens().data
                if (spotifyId == null || spotifyTokens == null || harmonyToken == null) {
                    return@withContext Resource.error(msgId = R.string.token_does_not_exist)
                }
                val obj = HarmonyIntegrateSpotifyBodyDto(
                    spotifyId = spotifyId,
                    accessToken = spotifyTokens.accessToken.value,
                    refreshToken = spotifyTokens.refreshToken.value
                )

                val response =
                    harmonyApi.integrateSpotify(
                        authToken = harmonyToken,
                        harmonyIntegrateSpotifyBodyDto = obj
                    )
                return@withContext response.mapToResourceBoolean()
            } catch (t: Throwable) {
                return@withContext Resource.error(msg = t.message)
            }
        }

    suspend fun disintegrateSpotify(): Resource<Boolean> =
        withContext(ioDispatcher) {
            try {
                val harmonyToken = userRepo.getHarmonyTokenFromCache().data
                    ?: return@withContext Resource.error(msgId = R.string.token_does_not_exist)
                val response = harmonyApi.disintegrateSpotify(authToken = harmonyToken)
                return@withContext response.mapToResourceBoolean()
            } catch (t: Throwable) {
                return@withContext Resource.error(msg = t.message)
            }
        }
}