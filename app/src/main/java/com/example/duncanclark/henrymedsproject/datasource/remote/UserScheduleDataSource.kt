package com.example.duncanclark.henrymedsproject.datasource.remote

import javax.inject.Inject

class UserScheduleDataSourceImpl @Inject constructor(): UserScheduleDataSource {
    // TODO DC: Replace with live endpoint and use retrofit
    override fun getSchedule(): String {
        return "Not implemented yet!"
    }

    override fun setSchedule(localDateTime: String) {
        TODO("Not yet implemented")
    }
}

interface UserScheduleDataSource {
    fun getSchedule(): String
    fun setSchedule(localDateTime: String)
}