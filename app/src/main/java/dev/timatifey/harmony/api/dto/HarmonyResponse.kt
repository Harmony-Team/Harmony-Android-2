package dev.timatifey.harmony.api.dto

import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName

data class HarmonyResponse(
    @SerializedName("message")
    val message: String,

    @SerializedName("code")
    val code: Int?,

    @SerializedName("response")
    val response: JsonElement?,

    @SerializedName("token")
    val token: String?
)