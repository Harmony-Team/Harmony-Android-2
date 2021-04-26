package dev.timatifey.harmony.repo.groups

import dev.timatifey.harmony.data.database.GroupsDao
import dev.timatifey.harmony.data.mappers.toHarmonyGroup
import dev.timatifey.harmony.data.model.harmony.HarmonyGroup
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GroupsRepoImpl @Inject constructor(
    private val groupsDao: GroupsDao
) : GroupsRepo {

    override fun getAllGroups(): Flow<List<HarmonyGroup>> =
        groupsDao.loadAllGroups()
            .map {
                it.map { groupEntity ->
                    groupEntity.toHarmonyGroup()
                }
            }

    override suspend fun addNewGroup(group: HarmonyGroup) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteGroup(group: HarmonyGroup) {
        TODO("Not yet implemented")
    }
}