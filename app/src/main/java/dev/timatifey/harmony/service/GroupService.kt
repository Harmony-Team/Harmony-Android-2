package dev.timatifey.harmony.service

import android.annotation.SuppressLint
import android.util.Log
import dev.timatifey.harmony.R
import dev.timatifey.harmony.api.harmony.HarmonyAPI
import dev.timatifey.harmony.data.model.harmony.HarmonyGroup
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import dev.timatifey.harmony.data.Resource
import dev.timatifey.harmony.data.Status
import dev.timatifey.harmony.data.mappers.toResourceGroup
import dev.timatifey.harmony.data.mappers.toResourceGroups
import dev.timatifey.harmony.repo.groups.GroupsRepo
import dev.timatifey.harmony.repo.user.UserRepo
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GroupService @Inject constructor(
    private val harmonyAPI: HarmonyAPI,
    private val groupRepo: GroupsRepo,
    private val ioDispatcher: CoroutineDispatcher,
    private val userRepo: UserRepo,
) {

    suspend fun getGroups(): Resource<List<HarmonyGroup>> =
        withContext(ioDispatcher) {
            try {
                val cachedGroups = groupRepo.getAllGroups().first()
                Log.e("GroupService", cachedGroups.toString())
                if (cachedGroups.isNotEmpty()) {
                    return@withContext Resource.success(cachedGroups)
                }
                Log.e("GroupService", "LOAD GROUPS")
                return@withContext fetchGroups()
            } catch (t: Throwable) {
                return@withContext Resource.error(msg = t.message)
            }
        }

    private suspend fun fetchGroups(): Resource<List<HarmonyGroup>> =
        withContext(ioDispatcher) {
            try {
                val harmonyToken = userRepo.getHarmonyTokenFromCache().data
                    ?: return@withContext Resource.error(msgId = R.string.token_does_not_exist)
                val groupsDto = harmonyAPI.getGroups(harmonyToken)
                val gr = groupsDto.toResourceGroups()
                Log.e("GDK", gr.toString())
                return@withContext gr
            } catch (t: Throwable) {
                return@withContext Resource.error(msg = t.message)
            }
        }

    @SuppressLint("SimpleDateFormat")
    suspend fun addGroup(
        groupName: String,
        description: String,
        imageUri: String?
    ): Resource<HarmonyGroup> =
        withContext(ioDispatcher) {
            try {
                val harmonyToken = userRepo.getHarmonyTokenFromCache().data
                    ?: return@withContext Resource.error(msgId = R.string.token_does_not_exist)
                val result = harmonyAPI.createGroup(
                    authToken = harmonyToken,
                    groupName = groupName,
                    description = description,
                    avatarUrl = imageUri,
                ).toResourceGroup()
                if (result.status is Status.Error) {
                    return@withContext Resource.error(msg = result.message.message)
                }
                val group = result.data!!
                groupRepo.addNewGroup(group)
                return@withContext Resource.success(group)
            } catch (t: Throwable) {
                return@withContext Resource.error(msg = t.message)
            }
        }

    suspend fun deleteGroup(group: HarmonyGroup): Resource<Boolean> =
        withContext(ioDispatcher) {
            try {
                groupRepo.deleteGroup(group)
                return@withContext Resource.success(true)
            } catch (t: Throwable) {
                return@withContext Resource.error(msg = t.message)
            }
        }

    suspend fun joinGroup(code: String): Resource<HarmonyGroup> =
        withContext(ioDispatcher) {
            try {
                val harmonyToken = userRepo.getHarmonyTokenFromCache().data
                    ?: return@withContext Resource.error(msgId = R.string.token_does_not_exist)
                val groupDto = harmonyAPI.joinGroup(harmonyToken, code)
                return@withContext groupDto.toResourceGroup(code)
            } catch (t: Throwable) {
                return@withContext Resource.error(msg = t.message)
            }
        }

    suspend fun getGroupById(id: Long): Resource<HarmonyGroup> =
        withContext(ioDispatcher) {
            try {
                val group = groupRepo.getGroupById(id).firstOrNull()
                return@withContext if (group != null)
                    Resource.success(group)
                else Resource.error(msg = "Not found")
            } catch (t: Throwable) {
                return@withContext Resource.error(msg = t.message)
            }
        }
}