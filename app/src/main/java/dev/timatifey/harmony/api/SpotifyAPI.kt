package dev.harmony.api

import dev.harmony.data.model.user.SpotifyUserProfile
import retrofit2.http.GET
import retrofit2.http.Header

interface SpotifyAPI {

    @GET("/v1/me")
    suspend fun getCurrentUser(@Header("Authorization") token: String): SpotifyUserProfile
}