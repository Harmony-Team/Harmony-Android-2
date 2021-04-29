package dev.timatifey.harmony.api.spotify

import dev.timatifey.harmony.api.spotify.dto.SpotifyUserPlaylistsDto
import dev.timatifey.harmony.api.spotify.dto.SpotifyUserProfileDto
import dev.timatifey.harmony.data.model.harmony.Token
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SpotifyAPI {

    @GET("/v1/me")
    suspend fun getCurrentUser(@Header("Authorization") token: Token): SpotifyUserProfileDto

    @GET("/v1/me")
    suspend fun getCurrentUserPlaylists(
        @Header("Authorization") token: Token,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): SpotifyUserPlaylistsDto
}