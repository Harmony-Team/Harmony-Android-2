package dev.timatifey.harmony.api.spotify.utils

import dev.timatifey.harmony.common.app.Config
import dev.timatifey.harmony.common.app.Config.Companion.SPOTIFY_SCOPES
import dev.timatifey.harmony.util.hashBySHA256WithBase64Encode

fun constructAuthorizationURI(codeVerifier: String): String {
    val params = mutableListOf<Pair<String, Any>>()

    params.add("client_id" to Config.SPOTIFY_CLIENT_ID)
    params.add("response_type" to "code")
    params.add("redirect_uri" to Config.SPOTIFY_REDIRECT_URI)
    params.add("code_challenge_method" to "S256")
    params.add("code_challenge" to codeVerifier.hashBySHA256WithBase64Encode())
    params.add("scope" to SPOTIFY_SCOPES)

    val paramString = params.joinToString(separator = "&") { "${it.first}=${it.second}" }
    return "https://accounts.spotify.com/authorize?$paramString"
}