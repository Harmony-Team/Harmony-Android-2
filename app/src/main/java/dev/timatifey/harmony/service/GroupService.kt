package dev.timatifey.harmony.service

import dev.timatifey.harmony.data.model.harmony.HarmonyGroup
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import dev.timatifey.harmony.data.Resource
import dev.timatifey.harmony.repo.groups.GroupsRepo
import dev.timatifey.harmony.util.randomString
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class GroupService @Inject constructor(
    private val groupRepo: GroupsRepo,
    private val ioDispatcher: CoroutineDispatcher,
) {

    suspend fun getGroups(): Resource<List<HarmonyGroup>> =
        withContext(ioDispatcher) {
            try {
                return@withContext Resource.success(generateGroup(10))
            } catch (t: Throwable) {
                return@withContext Resource.error(msg = t.message)
            }
        }
}

fun generateGroup(size: Int): List<HarmonyGroup> {
    val gr = mutableListOf<HarmonyGroup>()
    for (i in 1..size) {
        gr.add(
            HarmonyGroup(
                id = i.toLong(),
                name = randomString(5, 10),
                description = randomString(20, 24),
                hostLogin = null,
                users = null,
                avatarUrl = null,
                dateCreated = null,
            )
        )
    }
    return gr
}