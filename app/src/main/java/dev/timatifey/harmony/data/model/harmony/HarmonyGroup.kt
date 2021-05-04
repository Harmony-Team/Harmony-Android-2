package dev.timatifey.harmony.data.model.harmony

data class HarmonyGroup(
    val id: Long,
    val name: String,
    val description: String,
    var hostLogin: String,
    var users: MutableList<HarmonyGroupUser>,
    var avatarUrl: String?,
    var dateCreated: String,
    var inviteCode: String?,
)
