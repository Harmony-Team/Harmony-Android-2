package dev.timatifey.harmony.api.harmony.dto

import com.google.gson.annotations.SerializedName

data class HarmonyIntegrateSpotifyBodyDto (
    @SerializedName("spotifyId")
    var spotifyId: String,

    @SerializedName("accessToken")
    var accessToken: String,

    @SerializedName("refreshToken")
    var refreshToken: String,
)