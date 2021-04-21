package dev.timatifey.harmony.data.model.harmony

import dev.timatifey.harmony.data.model.spotify.SpotifyUserBody

data class User(
    val username: String,
    val email: String,
    val password: String,
    val harmonyToken: Token,
    val spotifyInfo: SpotifyUserBody?,
)

inline class Token(val value: String)