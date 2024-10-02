package com.example.duncanclark.henrymedsproject.datasource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface MyUserScheduleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert
}