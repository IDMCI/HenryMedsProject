package com.example.duncanclark.henrymedsproject.domain.repository

import com.example.duncanclark.henrymedsproject.domain.model.Event
import com.example.duncanclark.henrymedsproject.domain.model.Schedule
import com.example.duncanclark.henrymedsproject.domain.model.User
import com.example.duncanclark.henrymedsproject.domain.model.UserSchedule
import kotlinx.coroutines.flow.Flow

interface UserScheduleRepository {
    fun getSchedule(user: User): Flow<Schedule>
    fun setSchedule(
        user: User,
        schedule: UserSchedule,
        eventToSchedule: Event,
    ): Flow<Result<UserSchedule>>
}