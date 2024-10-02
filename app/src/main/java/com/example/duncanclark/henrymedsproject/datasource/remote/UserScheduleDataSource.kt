package com.example.duncanclark.henrymedsproject.datasource.remote

import javax.inject.Inject

class ScheduleDataSourceImpl @Inject constructor(): ScheduleDataSource {
    // TODO DC: Replace with live endpoint and use retrofit
    override fun getSchedule(): String {
        return "Not implemented yet!"
    }

    override fun setSchedule(localDateTime: String) {
        TODO("Not yet implemented")
    }
}

interface ScheduleDataSource {
    fun getSchedule(): String
    fun setSchedule(localDateTime: String)
}