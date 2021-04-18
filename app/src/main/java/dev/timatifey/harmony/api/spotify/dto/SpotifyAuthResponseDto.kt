package dev.timatifey.harmony.api.spotify.dto

import com.google.gson.annotations.SerializedName

data class SpotifyAuthResponseDto(
    @SerializedName("code")
    val code: String?,

    @SerializedName("state")
    val state: String?,

    @SerializedName("error")
    val error: String?
)
