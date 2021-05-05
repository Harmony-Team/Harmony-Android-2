package dev.timatifey.harmony.data.model.harmony


sealed class HarmonyLobbyItem

data class HarmonyGroupUser(
    val login: String,
    val isHost: Boolean,
    val isReady: Boolean,
    val avatarUrl: String?,
) : HarmonyLobbyItem()

object AddUserBtn : HarmonyLobbyItem()
