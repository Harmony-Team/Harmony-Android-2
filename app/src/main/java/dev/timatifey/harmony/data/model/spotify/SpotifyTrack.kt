package dev.timatifey.harmony.data.model.spotify

data class SpotifyTrack(
    val id: String,
    val name: String,
    val albumImage: String,
    val artistName: String,
    val durationMs: Long,
    val addedAt: String,
    val previewUrl: String,
)

data class SpotifyLobbyTrack(
    val id: String,
    val name: String,
    val albumImage: String,
    val artistName: String,
    val durationMs: Long,
    val addedAt: String,
    val previewUrl: String,

    var isSelected: Boolean,
    var isPlaying: Boolean,
)