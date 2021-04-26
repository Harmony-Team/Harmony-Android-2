package dev.timatifey.harmony.api.harmony.dto

import com.google.gson.annotations.SerializedName

data class HarmonyGroupsResponseDto(
    @SerializedName("сode")
    val code: Int,

    @SerializedName("message")
    val message: String?,

    @SerializedName("response")
    val groups: List<HarmonyGroupDto>?
)

data class HarmonyGroupDto(
    @SerializedName("id")
    val id: Long,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("hostLogin")
    val hostLogin: String,

    @SerializedName("users")
    val users: List<HarmonyUserDto>,

    @SerializedName("avatar_url")
    var avatarUrl: String?,
)