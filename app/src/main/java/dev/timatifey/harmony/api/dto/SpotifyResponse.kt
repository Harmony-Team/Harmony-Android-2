package dev.timatifey.harmony.api.dto

import com.google.gson.annotations.SerializedName

data class SpotifyResponse(
    @SerializedName("access_token")
    val accessToken: String?,

    @SerializedName("token_type")
    val tokenType: String?,

    @SerializedName("expires_in")
    val expiresIn: Long?,

    @SerializedName("state")
    val state: String?,

    @SerializedName("error")
    val error: String?
)

data class SpotifyAuthResponse(
    @SerializedName("code")
    val code: String?,

    @SerializedName("state")
    val state: String?,

    @SerializedName("error")
    val error: String?
)

data class SpotifyTokenResponse(
    @SerializedName("access_token")
    val accessToken: String,

    @SerializedName("token_type")
    val tokenType: String,

    @SerializedName("scope")
    val scope: String,

    @SerializedName("expires_in")
    val expiresIn: Int,

    @SerializedName("refresh_token")
    val refreshToken: String,
)