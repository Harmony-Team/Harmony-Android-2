package dev.timatifey.harmony.data.mappers

import com.google.gson.Gson
import dev.timatifey.harmony.Config
import dev.timatifey.harmony.api.harmony.dto.*
import dev.timatifey.harmony.data.Resource
import dev.timatifey.harmony.data.ResponseHandler
import dev.timatifey.harmony.data.model.harmony.HarmonyGroup
import dev.timatifey.harmony.data.model.harmony.Token
import java.lang.Exception

fun HarmonyAuthResponseDto.mapToResourceToken(): Resource<Token> {
    return if (code == Config.SUCCESS_CODE) {
        ResponseHandler.handleSuccess(Token(token!!))
    } else {
        ResponseHandler.handleException(exception = Exception(message), code = code)
    }
}

fun HarmonyUserResponseDto.mapToResourceUserDto(gson: Gson): Resource<HarmonyUserDto> {
    return if (code == Config.SUCCESS_CODE && users != null) {
        ResponseHandler.handleSuccess(users[0])
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