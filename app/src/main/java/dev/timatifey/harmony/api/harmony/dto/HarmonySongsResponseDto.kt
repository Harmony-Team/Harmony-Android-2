package dev.timatifey.harmony.api.harmony.dto

import com.google.gson.annotations.SerializedName

data class HarmonySongsResponseDto(
    @SerializedName("сode")
    val code: Int,

    @SerializedName("response")
    val songs: List<HarmonySongDto>?
)

data class HarmonySongDto(
    @SerializedName("userLogin")
    val username: String,

    @SerializedName("spotifyTrackId")
    val spotifyTrackId: String,
)