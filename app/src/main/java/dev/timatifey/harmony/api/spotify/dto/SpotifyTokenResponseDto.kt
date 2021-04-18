package dev.timatifey.harmony.api.spotify.dto

import com.google.gson.annotations.SerializedName

data class SpotifyTokenResponseDto(
    @SerializedName("access_token")
    val accessToken: String?,

    @SerializedName("token_type")
    val tokenType: String?,

    @SerializedName("scope")
    val scope: String?,

    @SerializedName("expires_in")
    val expiresIn: Int?,

    @SerializedName("refresh_token")
    val refreshToken: String?,
)