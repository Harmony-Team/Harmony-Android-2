package dev.timatifey.harmony.api.harmony.dto

import com.google.gson.annotations.SerializedName

data class HarmonyUserResponseDto(
    @SerializedName("сode")
    val code: Int,

    @SerializedName("message")
    val message: String?,

    @SerializedName("response")
    val users: List<HarmonyUserDto>?
)

data class HarmonyUserDto(
    @SerializedName("login")
    val username: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("role")
    val role: Role,

    @SerializedName("status")
    val status: Status,

    @SerializedName("dateCreated")
    val dateCreated: String?,

    @SerializedName("spotify")
    var spotifyHarmony: HarmonyIntegrateSpotifyBodyDto?,
)

enum class Role(val stringAssociation: String) {
    DEVELOPER("DEV"),
    USER("USER"),
}

enum class Status(val stringAssociation: String) {
    ACTIVE("ACTIVE"),
    BANNED("BANNED"),
}