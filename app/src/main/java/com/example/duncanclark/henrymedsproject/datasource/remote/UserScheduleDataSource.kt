package com.example.duncanclark.henrymedsproject.datasource.remote

import com.example.duncanclark.henrymedsproject.domain.model.UserSchedule
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PUT
import javax.inject.Inject

// Replace with live endpoint and use retrofit
interface UserScheduleDataSource {
    @GET("<Add parameters here>")
    fun getSchedule(): Call<UserSchedule>

    @PUT("<Add parameters here>")
    fun setSchedule(localDateTime: String): Call<Unit>
}

// Created for temporary injection purposes. Can be removed.
class UserScheduleDataSourceImpl @Inject constructor(): UserScheduleDataSource {
    override fun getSchedule(): Call<UserSchedule> {
        throw(Exception("Not getSchedule implemented"))
    }

    override fun setSchedule(localDateTime: String): Call<Unit> {
        throw(Exception("Not setSchedule implemented"))
    }
}