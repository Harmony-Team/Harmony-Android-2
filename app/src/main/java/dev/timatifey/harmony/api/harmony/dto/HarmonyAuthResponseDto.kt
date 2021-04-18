package dev.timatifey.harmony.api.harmony.dto

import com.google.gson.annotations.SerializedName

data class HarmonyAuthResponseDto(
    @SerializedName("сode")
    val code: Int,

    @SerializedName("message")
    val message: String?,

    @SerializedName("token")
    val token: String?,
)
