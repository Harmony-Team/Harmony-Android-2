package dev.timatifey.harmony.data.model.spotify

data class SpotifyTokens(
    val accessToken: String,
    val expiresIn: Int,
    val refreshToken: String,
)
