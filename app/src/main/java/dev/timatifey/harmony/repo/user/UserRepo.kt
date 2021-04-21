package dev.timatifey.harmony.repo.user

import dev.timatifey.harmony.data.Resource
import dev.timatifey.harmony.data.model.harmony.Token
import dev.timatifey.harmony.data.model.harmony.User
import dev.timatifey.harmony.data.model.settings.SettingFields
import dev.timatifey.harmony.data.model.spotify.SpotifyTokens

interface UserRepo {
    fun getHarmonyTokenFromCache(): Resource<Token>
    fun getSpotifyTokens(): Resource<SpotifyTokens>

    fun saveHarmonyTokenToCache(token: Token)
    fun saveSpotifyToken(spotifyTokens: SpotifyTokens)

    fun clearHarmonyTokenFromCache()

    fun saveHarmonyUser(harmonyUser: User)
    fun getHarmonyUser(): Resource<User>
    fun getHarmonyUserSettings(): Resource<SettingFields>
}