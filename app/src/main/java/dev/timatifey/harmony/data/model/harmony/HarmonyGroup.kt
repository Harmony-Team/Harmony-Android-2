package dev.timatifey.harmony.data.model.harmony

import dev.timatifey.harmony.api.harmony.dto.HarmonyUserDto

data class HarmonyGroup(
    val id: Int,
    val name: String,
    val description: String,
    val hostLogin: String,
    val users: List<HarmonyUserDto>,
    var avatarUrl: String?,
)
