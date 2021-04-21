package dev.timatifey.harmony.service

import dev.timatifey.harmony.api.harmony.HarmonyAPI
import dev.timatifey.harmony.api.harmony.dto.HarmonyAuthResponseDto
import dev.timatifey.harmony.api.spotify.SpotifyAuthAPI
import dev.timatifey.harmony.data.Resource
import dev.timatifey.harmony.data.mappers.mapToResourceToken
import dev.timatifey.harmony.data.model.harmony.Token
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

    // HARMONY
    suspend fun authHarmony(username: String, password: String): Resource<Token> =
        withContext(ioDispatcher) {
            try {
                val authDto: HarmonyAuthResponseDto = harmonyApi.authUser(username, password)
                return@withContext authDto.mapToResourceToken()
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
                return@withContext authDto.mapToResourceToken()
            } catch (t: Throwable) {
                return@withContext Resource.error(msg = t.message)
            }
        }


    fun authCacheIsValid(): Resource<Token> =
        try {
            Thread.sleep(2000)// emulating http request to server
            //TODO("Local Cache check on auth token valid")
            Resource.success(Token(randomString(40, 60)))
//            Resource.error(msg = "Auth Cache doesn't exist")
        } catch (t: Throwable) {
            Resource.error(t.message)
        }

    suspend fun recoveryAcc(email: String): Resource<Token> =
        withContext(ioDispatcher) {
            try {
                delay(5000)
                //TODO("recovery harmony acc")
                return@withContext Resource.success(Token(randomString(50, 60)))
            } catch (t: Throwable) {
                return@withContext Resource.error(msg = t.message)
            }
        }

//    private suspend fun integrateSpotify(
//        token: String,
//        spotifyId: String,
//        accessToken: String,
//        refreshToken: String
//    ): Resource<Boolean> {
//        return try {
//            val obj = IntegrateSpotifyBody(
//                spotifyId = spotifyId,
//                accessToken = accessToken,
//                refreshToken = refreshToken
//            )
//            val response =
//                harmonyApi.integrateSpotify(authToken = token, integrateSpotifyBody = obj)
//            if (response.code != Config.SUCCESS_CODE) {
//                ResponseHandler.handleException<HarmonyUser>(
//                    exception = AuthException(response.message),
//                    code = response.code
//                )
//            }
//            ResponseHandler.handleSuccess(true)
//        } catch (e: Exception) {
//            when (e) {
//                is HttpException -> {
//                    val code = JSONObject(e.response()!!.errorBody()!!.string()).getInt("code")
//                    ResponseHandler.handleException(e, code)
//                }
//                else -> ResponseHandler.handleException(e)
//            }
//        }
//    }
//
//    private suspend fun disintegrateSpotify(token: String): Resource<Boolean> {
//        return try {
//            val response = harmonyApi.disintegrateSpotify(authToken = token)
//            if (response.code != Config.SUCCESS_CODE) {
//                ResponseHandler.handleException<HarmonyUser>(
//                    exception = AuthException(response.message),
//                    code = response.code
//                )
//            }
//            ResponseHandler.handleSuccess(true)
//        } catch (e: Exception) {
//            when (e) {
//                is HttpException -> {
//                    val code = JSONObject(e.response()!!.errorBody()!!.string()).getInt("code")
//                    ResponseHandler.handleException(e, code)
//                }
//                else -> ResponseHandler.handleException(e)
//            }
//        }
//    }
//
//    // SPOTIFY
//
//    suspend fun exchangesCodeForAccessToken(
//        code: String,
//        codeVerifier: String
//    ): Resource<SpotifyTokenResponse> {
//        return try {
//            val spotifyAuthResponse = spotifyAuthAPI.exchangesCodeForToken(
//                clientId = SPOTIFY_CLIENT_ID,
//                grantType = "authorization_code",
//                redirectURI = SPOTIFY_REDIRECT_URI,
//                code = code,
//                codeVerifier = codeVerifier
//            )
//            ResponseHandler.handleSuccess(spotifyAuthResponse)
//        } catch (e: Exception) {
//            ResponseHandler.handleException(e)
//        }
//    }
//
//    suspend fun refreshToken(refreshToken: String): Resource<SpotifyTokenResponse> {
//        return try {
//            val spotifyAuthResponse = spotifyAuthAPI.refreshToken(
//                clientId = SPOTIFY_CLIENT_ID,
//                grantType = "refresh_token",
//                refreshToken = refreshToken
//            )
//            ResponseHandler.handleSuccess(spotifyAuthResponse)
//        } catch (e: Exception) {
//            ResponseHandler.handleException(e)
//        }
//    }


}