package dev.timatifey.harmony.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = GroupEntity.TABLE_NAME)
data class GroupEntity(
    @PrimaryKey
    val id: Long,
    var name: String,
    var description: String,
    val imageUrl: String?,
    val dateCreated: String,
    val hostLogin: String,
) {
    companion object {
        const val TABLE_NAME = "groups_table"
    }
}
