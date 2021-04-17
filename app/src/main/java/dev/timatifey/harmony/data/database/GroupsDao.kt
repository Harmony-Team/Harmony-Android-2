package dev.timatifey.harmony.data.database

import androidx.room.*
import dev.timatifey.harmony.data.entities.GroupEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GroupsDao {

    @Insert(entity = GroupEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGroup(group: GroupEntity)

    @Query("SELECT * FROM ${GroupEntity.TABLE_NAME} ORDER by dateCreated DESC")
    fun loadAllGroups(): Flow<List<GroupEntity>>

    @Update
    suspend fun updateGroup(group: GroupEntity)

    @Delete
    suspend fun deleteGroup(group: GroupEntity)

}