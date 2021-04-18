package dev.timatifey.harmony.data.model.harmony

data class HarmonyPlaylist(
    val name: String,
    val tracksCount: Int,
    val participants: Pair<Int, Int>,
    val imageUrl: String,
)
