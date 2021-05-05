package dev.timatifey.harmony.data.mappers

import dev.timatifey.harmony.R
import dev.timatifey.harmony.app.Config
import dev.timatifey.harmony.api.spotify.dto.SpotifyTokenResponseDto
import dev.timatifey.harmony.api.spotify.dto.SpotifyUserProfileDto
import dev.timatifey.harmony.data.Resource
import dev.timatifey.harmony.data.ResponseHandler
import dev.timatifey.harmony.data.model.harmony.Token
import dev.timatifey.harmony.data.model.spotify.SpotifyTokens
import dev.timatifey.harmony.data.model.spotify.SpotifyUserBody

fun SpotifyTokenResponseDto.toResourceSpotifyTokens(): Resource<SpotifyTokens> {
    return if (accessToken != null) {
        ResponseHandler.handleSuccess(
            SpotifyTokens(
                accessToken = Token(accessToken),
                expiresIn = expiresIn!!,
                refreshToken = Token(refreshToken!!),
            )
        )
    } else {
        ResponseHandler.handleException(code = Config.SPOTIFY_TOKENS_ERROR_CODE)
    }
}

fun SpotifyUserProfileDto.toResourceSpotifyUserBody(): Resource<SpotifyUserBody> {
    if (id != null) {
        return ResponseHandler.handleSuccess(
            SpotifyUserBody(
                id = id,
                name = name!!,
                email = email!!,
            )
        )
    } else {
        if (spotifyErrorObj != null && spotifyErrorObj.status == 401) {
            return Resource.error(msgId = R.string.spotify_access_token_expired)
        }
        return ResponseHandler.handleException(code = Int.MAX_VALUE)
    }
}