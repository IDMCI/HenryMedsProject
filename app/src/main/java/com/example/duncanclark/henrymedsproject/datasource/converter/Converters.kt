package com.example.duncanclark.henrymedsproject.datasource.converter

import androidx.room.TypeConverter
import com.example.duncanclark.henrymedsproject.domain.model.Event
import com.example.duncanclark.henrymedsproject.domain.model.User
import com.example.duncanclark.henrymedsproject.domain.model.UserSchedule
import com.example.duncanclark.henrymedsproject.domain.model.UserType
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class Converters @Inject constructor(
) {
    @TypeConverter
    fun fromUser(value: User): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toUser(value: String): User {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromUserSchedule(value: UserSchedule): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toUserSchedule(value: String): UserSchedule {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromSchedule(value: List<Event>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toSchedule(value: String): List<Event> {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromLocalDateTime(value: LocalDateTime): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toLocalDateTime(value: String): LocalDateTime {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromUserType(value: UserType): String {
        return value.type
    }

    @TypeConverter
    fun toUserType(value: String): UserType {
        return when (value) {
            UserType.CLIENT.type -> UserType.CLIENT
            UserType.PROVIDER.type -> UserType.PROVIDER
            else -> UserType.UNKNOWN
        }
    }
}