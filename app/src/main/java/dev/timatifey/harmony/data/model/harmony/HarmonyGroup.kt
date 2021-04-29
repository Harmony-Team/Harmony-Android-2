package dev.timatifey.harmony.data.model.harmony

import dev.timatifey.harmony.api.harmony.dto.HarmonyUserDto

data class HarmonyGroup(
    val id: Long,
    val name: String,
    val description: String,
    var hostLogin: String?,
    var users: List<HarmonyUserDto>?,
    var avatarUrl: String?,
    var dateCreated: String,
    var shareLink: String?
)
