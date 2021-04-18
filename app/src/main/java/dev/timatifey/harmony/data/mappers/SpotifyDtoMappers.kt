package dev.timatifey.harmony.data.mappers

import dev.timatifey.harmony.Config
import dev.timatifey.harmony.api.spotify.dto.SpotifyTokenResponseDto
import dev.timatifey.harmony.api.spotify.dto.SpotifyUserProfileDto
import dev.timatifey.harmony.data.Resource
import dev.timatifey.harmony.data.ResponseHandler
import dev.timatifey.harmony.data.model.spotify.SpotifyTokens
import dev.timatifey.harmony.data.model.spotify.SpotifyUserBody

fun SpotifyTokenResponseDto.mapToResourceSpotifyTokens(): Resource<SpotifyTokens> {
    return if (accessToken != null) {
        ResponseHandler.handleSuccess(
            SpotifyTokens(
                accessToken = accessToken,
                expiresIn = expiresIn!!,
                refreshToken = refreshToken!!,
            )
        )
    } else {
        ResponseHandler.handleException(code = Config.SPOTIFY_TOKENS_ERROR_CODE)
    }
}

fun SpotifyUserProfileDto.mapToResourceSpotifyUserBody(): Resource<SpotifyUserBody> {
    return if (id != null) {
        ResponseHandler.handleSuccess(
            SpotifyUserBody(
                id = id,
                name = name!!,
                email = email!!,
            )
        )
    } else {
        ResponseHandler.handleException(code = Int.MAX_VALUE)
    }
}