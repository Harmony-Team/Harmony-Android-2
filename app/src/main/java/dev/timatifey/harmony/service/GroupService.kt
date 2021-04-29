package dev.timatifey.harmony.service

import android.annotation.SuppressLint
import dev.timatifey.harmony.R
import dev.timatifey.harmony.api.harmony.HarmonyAPI
import dev.timatifey.harmony.data.model.harmony.HarmonyGroup
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import dev.timatifey.harmony.data.Resource
import dev.timatifey.harmony.data.Status
import dev.timatifey.harmony.data.mappers.mapToResourceBoolean
import dev.timatifey.harmony.data.mappers.mapToResourceGroups
import dev.timatifey.harmony.repo.groups.GroupsRepo
import dev.timatifey.harmony.repo.user.UserRepo
import dev.timatifey.harmony.util.generateShareLink
import dev.timatifey.harmony.util.randomString
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.single
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

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
//                val cachedGroups = groupRepo.getAllGroups().single()
//                if (cachedGroups.isNotEmpty()) {
//                    return@withContext Resource.success(cachedGroups)
//                }
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
                return@withContext groupsDto.mapToResourceGroups()
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
                ).mapToResourceBoolean()
                if (result.status is Status.Error) {
                    return@withContext Resource.error(msg = result.message.message)
                }
                val today = Date()
                val format = SimpleDateFormat("yyyy-MM-dd")
                val dateToStr: String = format.format(today.time)
                val group = HarmonyGroup(
                    id = today.time,
                    name = groupName,
                    description = description,
                    hostLogin = null,
                    users = null,
                    avatarUrl = imageUri,
                    dateCreated = dateToStr,
                    shareLink = generateShareLink(today.time),
                )
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
}