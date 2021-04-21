package dev.timatifey.harmony.data.model.spotify

import dev.timatifey.harmony.data.model.harmony.Token

data class SpotifyTokens(
    val accessToken: Token,
    val expiresIn: Int?,
    val refreshToken: Token,
)
