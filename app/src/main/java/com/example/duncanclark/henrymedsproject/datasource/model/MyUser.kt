package com.example.duncanclark.henrymedsproject.datasource.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.duncanclark.henrymedsproject.domain.model.User
import com.example.duncanclark.henrymedsproject.domain.model.UserSchedule
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "provider_user_table")
data class ProviderUser(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val user: User,
    val userSchedule: UserSchedule
)