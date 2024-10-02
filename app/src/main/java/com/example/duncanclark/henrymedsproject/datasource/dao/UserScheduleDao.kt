package com.example.duncanclark.henrymedsproject.datasource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.duncanclark.henrymedsproject.datasource.model.Client
import com.example.duncanclark.henrymedsproject.datasource.model.Provider

@Dao
interface UserScheduleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClient(user: Client)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProvider(user: Provider)

    @Query("SELECT * FROM client_user_table WHERE id = :id")
    suspend fun getClientById(id: Long): Client

    @Query("SELECT * FROM provider_user_table WHERE id = :id")
    suspend fun getProviderById(id: Long): Provider
}