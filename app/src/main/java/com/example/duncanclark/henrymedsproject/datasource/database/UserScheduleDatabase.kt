package com.example.duncanclark.henrymedsproject.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.duncanclark.henrymedsproject.datasource.converter.Converters
import com.example.duncanclark.henrymedsproject.datasource.dao.UserScheduleDao
import com.example.duncanclark.henrymedsproject.datasource.model.Client
import com.example.duncanclark.henrymedsproject.datasource.model.Provider

@Database(
    entities = [Provider::class, Client::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class UserScheduleDatabase: RoomDatabase() {
    abstract val userScheduleDao: UserScheduleDao

    companion object {
        const val DATABASE_NAME = "user_schedule_db"
    }
}