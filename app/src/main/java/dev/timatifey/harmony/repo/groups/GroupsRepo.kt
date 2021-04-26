package dev.timatifey.harmony.repo.groups

import dev.timatifey.harmony.data.model.harmony.HarmonyGroup
import kotlinx.coroutines.flow.Flow

interface GroupsRepo {
    fun getAllGroups(): Flow<List<HarmonyGroup>>
    suspend fun addNewGroup(group: HarmonyGroup)
    suspend fun deleteGroup(group: HarmonyGroup)
}