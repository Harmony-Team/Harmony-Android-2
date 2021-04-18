package dev.timatifey.harmony.api.harmony.dto

import com.google.gson.annotations.SerializedName

data class HarmonyJoinGroupResponseDto(
    @SerializedName("сode")
    val code: Int,

    @SerializedName("message")
    val message: String?,

    @SerializedName("object")
    val group: HarmonyGroupDto?,
)
