package dev.timatifey.harmony.repo.user

import android.content.SharedPreferences
import dev.timatifey.harmony.R
import dev.timatifey.harmony.common.app.Config.Companion.APP_PREFERENCES_TOKEN
import dev.timatifey.harmony.data.Resource
import dev.timatifey.harmony.data.model.harmony.Token
import dev.timatifey.harmony.data.model.harmony.User
import dev.timatifey.harmony.data.model.settings.SettingFields
import dev.timatifey.harmony.data.model.spotify.SpotifyTokens
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepoImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : UserRepo {

    private var harmonyUser: User? = null
    private var spotifyTokens: SpotifyTokens? = null

    private fun <T> saveInSharedPreferences(key: String, value: T) {
        val ed = sharedPreferences.edit()
        when (value) {
            is String -> ed.putString(key, value).apply()
            is Boolean -> ed.putBoolean(key, value).apply()
        }
    }

    private fun clearFromSharedPreferences(key: String) {
        sharedPreferences
            .edit()
            .remove(key)
            .apply()
    }

    override fun getHarmonyTokenFromCache(): Resource<Token> {
        val tokenFromShare = sharedPreferences.getString(APP_PREFERENCES_TOKEN, null)
            ?: return Resource.error(R.string.token_does_not_exist)
        return Resource.success(Token(tokenFromShare))
    }

    override fun getSpotifyTokens(): Resource<SpotifyTokens> =
        if (spotifyTokens != null)
            Resource.success(spotifyTokens!!)
        else Resource.error(R.string.token_does_not_exist)


    override fun saveHarmonyTokenToCache(token: Token) {
        saveInSharedPreferences(APP_PREFERENCES_TOKEN, token.value)
    }

    override fun saveSpotifyToken(spotifyTokens: SpotifyTokens) {
        this.spotifyTokens = spotifyTokens
    }

    override fun clearHarmonyTokenFromCache() {
        clearFromSharedPreferences(APP_PREFERENCES_TOKEN)
    }

    override fun saveHarmonyUser(harmonyUser: User) {
        this.harmonyUser = harmonyUser
    }

    override fun getHarmonyUser(): Resource<User> =
        if (harmonyUser != null) {
            Resource.success(harmonyUser!!)
        } else {
            Resource.error(R.string.user_does_not_exist)
        }

    override fun getHarmonyUserSettings(): Resource<SettingFields> =
        if (harmonyUser != null) {
            Resource.success(
                SettingFields(
                    email = harmonyUser!!.email,
                    password = harmonyUser!!.password,
                    isSpotifyAuth = harmonyUser!!.spotifyInfo != null
                )
            )
        } else {
            Resource.error(R.string.user_does_not_exist)
        }

}