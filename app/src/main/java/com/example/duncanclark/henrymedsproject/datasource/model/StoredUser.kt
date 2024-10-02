package com.example.duncanclark.henrymedsproject.datasource.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.duncanclark.henrymedsproject.domain.model.Event
import com.example.duncanclark.henrymedsproject.domain.model.User
import com.example.duncanclark.henrymedsproject.domain.model.UserSchedule
import com.example.duncanclark.henrymedsproject.domain.model.UserType
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "provider_user_table")
data class Provider(
    @PrimaryKey(autoGenerate = true) override val id: Long = 0,
    override val user: User = User(0, UserType.PROVIDER),
    override val userSchedule: UserSchedule = UserSchedule(0, emptyList<Event>(), emptyList<Event>())
): StoredUser

@Serializable
@Entity(tableName = "client_user_table")
data class Client(
    @PrimaryKey(autoGenerate = true) override val id: Long = 0,
    override val user: User = User(0, UserType.CLIENT),
    override val userSchedule: UserSchedule = UserSchedule(0, emptyList<Event>(), emptyList<Event>())
): StoredUser

interface StoredUser {
    val id: Long
    val user: User
    val userSchedule: UserSchedule
}