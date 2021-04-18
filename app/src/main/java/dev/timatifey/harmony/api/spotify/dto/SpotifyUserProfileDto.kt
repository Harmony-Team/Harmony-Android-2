package dev.timatifey.harmony.api.spotify.dto

import com.google.gson.annotations.SerializedName

data class SpotifyUserProfileDto(
    @SerializedName("id")
    val id: String?,

    @SerializedName("display_name")
    val name: String?,

    @SerializedName("email")
    val email: String?,

)
