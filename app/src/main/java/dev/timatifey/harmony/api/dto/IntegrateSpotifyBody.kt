package dev.timatifey.harmony.api.dto

import com.google.gson.annotations.SerializedName

data class IntegrateSpotifyBody (
    @SerializedName("spotifyId")
    var spotifyId: String,

    @SerializedName("accessToken")
    var accessToken: String,

    @SerializedName("refreshToken")
    var refreshToken: String,
)