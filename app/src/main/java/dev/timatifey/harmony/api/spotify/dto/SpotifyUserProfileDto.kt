package dev.timatifey.harmony.api.spotify.dto

import com.google.gson.annotations.SerializedName

data class SpotifyUserProfileDto(
    @SerializedName("id")
    val id: String?,

    @SerializedName("display_name")
    val name: String?,

    @SerializedName("email")
    val email: String?,

    @SerializedName("error")
    val spotifyErrorObj: SpotifyErrorObj?
)

data class SpotifyErrorObj(
    @SerializedName("status")
    val status: Int,

    @SerializedName("message")
    val message: String
)