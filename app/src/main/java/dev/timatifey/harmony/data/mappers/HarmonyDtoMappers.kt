package dev.timatifey.harmony.data.mappers

import android.util.Log
import dev.timatifey.harmony.app.Config
import dev.timatifey.harmony.api.harmony.dto.*
import dev.timatifey.harmony.data.Resource
import dev.timatifey.harmony.data.ResponseHandler
import dev.timatifey.harmony.data.entities.GroupEntity
import dev.timatifey.harmony.data.model.harmony.HarmonyGroup
import dev.timatifey.harmony.data.model.harmony.HarmonyGroupUser
import dev.timatifey.harmony.data.model.harmony.Token
import dev.timatifey.harmony.data.model.spotify.SpotifyTokens
import java.lang.Exception

fun HarmonyAuthResponseDto.toResourceToken(): Resource<Token> {
    return if (code == Config.SUCCESS_CODE) {
        ResponseHandler.handleSuccess(Token(token!!))
    } else {
        ResponseHandler.handleException(exception = Exception(message), code = code)
    }
}

fun HarmonyUserResponseDto.toResourceUserDto(): Resource<HarmonyUserDto> {
    return if (code == Config.SUCCESS_CODE && users != null) {
        val userDto = users[0]
        ResponseHandler.handleSuccess(userDto)
    } else {
        ResponseHandler.handleException(exception = Exception(message), code = code)
    }
}

fun HarmonySimpleResponseDto.toResourceBoolean(): Resource<Boolean> {
    return if (code == Config.SUCCESS_CODE) {
        ResponseHandler.handleSuccess(true)
    } else {
        ResponseHandler.handleException(exception = Exception(message), code = code)
    }
}

fun HarmonyGroupsResponseDto.toResourceGroups(): Resource<List<HarmonyGroup>> {
    return if (code == Config.SUCCESS_CODE && groups != null) {
        ResponseHandler.handleSuccess(groups.map {
            Log.e("FF", it.toString())
            it.toHarmonyGroup()
        })
    } else {
        ResponseHandler.handleException(exception = Exception(message), code = code)
    }
}

fun HarmonyGroupResponseDto.toResourceGroupWithInviteCode(): Resource<Pair<HarmonyGroup, String?>> {
    return if (code == Config.SUCCESS_CODE && group != null) {
        ResponseHandler.handleSuccess(group.toHarmonyGroup() to this.inviteCode)
    } else {
        ResponseHandler.handleException(exception = Exception(message), code = code)
    }
}

fun HarmonyGroupResponseDto.toResourceGroupWithoutInviteCode(): Resource<HarmonyGroup> {
    return if (code == Config.SUCCESS_CODE && group != null) {
        ResponseHandler.handleSuccess(group.toHarmonyGroup())
    } else {
        ResponseHandler.handleException(exception = Exception(message), code = code)
    }
}

fun HarmonyIntegrateSpotifyBodyDto.toSpotifyTokens(): SpotifyTokens =
    SpotifyTokens(
        accessToken = Token(this.accessToken),
        expiresIn = null,
        refreshToken = Token(this.refreshToken),
    )

fun GroupEntity.toHarmonyGroup(): HarmonyGroup =
    HarmonyGroup(
        id = this.id,
        name = this.name,
        description = this.description,
        hostLogin = this.hostLogin,
        users = mutableListOf<HarmonyGroupUser>(),
        avatarUrl = this.imageUrl,
        dateCreated = this.dateCreated,
    )

fun HarmonyGroup.toGroupEntity(): GroupEntity =
    GroupEntity(
        id = this.id,
        name = this.name,
        description = this.description,
        imageUrl = this.avatarUrl,
        hostLogin = this.hostLogin,
        dateCreated = this.dateCreated,
    )

fun HarmonyGroupDto.toHarmonyGroup(): HarmonyGroup {
    return HarmonyGroup(
        id = this.id,
        name = this.name,
        description = this.description,
        hostLogin = this.hostLogin,
        users = this.users.map { it.toHarmonyGroupUser(this.hostLogin) }.toMutableList(),
        avatarUrl = this.avatarUrl,
        dateCreated = this.dateCreated,
    )
}

fun HarmonyGroupDto.GroupUserDto.toHarmonyGroupUser(hostLogin: String): HarmonyGroupUser =
    HarmonyGroupUser(
        login = this.login,
        avatarUrl = null,
        isHost = this.login == hostLogin,
        isReady = false,
    )