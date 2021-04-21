package dev.timatifey.harmony.api.spotify

import dev.timatifey.harmony.api.spotify.dto.SpotifyUserProfileDto
import dev.timatifey.harmony.data.model.harmony.Token
import retrofit2.http.GET
import retrofit2.http.Header

interface SpotifyAPI {

    @GET("/v1/me")
    suspend fun getCurrentUser(@Header("Authorization") token: Token): SpotifyUserProfileDto
}