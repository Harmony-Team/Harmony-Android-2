package dev.timatifey.harmony.data.mappers

import dev.timatifey.harmony.common.app.Config
import dev.timatifey.harmony.api.harmony.dto.*
import dev.timatifey.harmony.data.Resource
import dev.timatifey.harmony.data.ResponseHandler
import dev.timatifey.harmony.data.model.harmony.HarmonyGroup
import dev.timatifey.harmony.data.model.harmony.Token
import dev.timatifey.harmony.data.model.harmony.User
import dev.timatifey.harmony.data.model.spotify.SpotifyTokens
import dev.timatifey.harmony.data.model.spotify.SpotifyUserBody
import java.lang.Exception

fun HarmonyAuthResponseDto.mapToResourceToken(): Resource<Token> {
    return if (code == Config.SUCCESS_CODE) {
        ResponseHandler.handleSuccess(Token(token!!))
    } else {
        ResponseHandler.handleException(exception = Exception(message), code = code)
    }
}

fun HarmonyUserResponseDto.mapToResourceUserDto(): Resource<HarmonyUserDto> {
    return if (code == Config.SUCCESS_CODE && users != null) {
        val userDto = users[0]
        ResponseHandler.handleSuccess(userDto)
    } else {
        ResponseHandler.handleException(exception = Exception(message), code = code)
    }
}

fun HarmonySimpleResponseDto.mapToResourceBoolean(): Resource<Boolean> {
    return if (code == Config.SUCCESS_CODE) {
        ResponseHandler.handleSuccess(true)
    } else {
        ResponseHandler.handleException(exception = Exception(message), code = code)
    }
}

fun HarmonyGroupsResponseDto.mapToResourceGroups(): Resource<List<HarmonyGroup>> {
    return if (code == Config.SUCCESS_CODE && groups != null) {
        ResponseHandler.handleSuccess(groups.map { harmonyGroupDto ->
            HarmonyGroup(
                id = harmonyGroupDto.id,
                name = harmonyGroupDto.name,
                description = harmonyGroupDto.description,
                hostLogin = harmonyGroupDto.hostLogin,
                users = harmonyGroupDto.users,
                avatarUrl = harmonyGroupDto.avatarUrl,
            )
        })
    } else {
        ResponseHandler.handleException(exception = Exception(message), code = code)
    }
}

fun HarmonyJoinGroupResponseDto.mapToResourceGroup(): Resource<HarmonyGroup> {
    return if (code == Config.SUCCESS_CODE && group != null) {
        ResponseHandler.handleSuccess(
            HarmonyGroup(
                id = group.id,
                name = group.name,
                description = group.description,
                hostLogin = group.hostLogin,
                users = group.users,
                avatarUrl = group.avatarUrl,
            )
        )
    } else {
        ResponseHandler.handleException(exception = Exception(message), code = code)
    }
}

fun HarmonyIntegrateSpotifyBodyDto.mapToSpotifyTokens(): SpotifyTokens =
    SpotifyTokens(
        accessToken = Token(this.accessToken),
        expiresIn = null,
        refreshToken = Token(this.refreshToken),
    )