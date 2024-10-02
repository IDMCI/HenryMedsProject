package com.example.duncanclark.henrymedsproject.datasource.repository

import com.example.duncanclark.henrymedsproject.datasource.remote.ScheduleDataSource
import com.example.duncanclark.henrymedsproject.domain.model.Event
import com.example.duncanclark.henrymedsproject.domain.model.Schedule
import com.example.duncanclark.henrymedsproject.domain.model.User
import com.example.duncanclark.henrymedsproject.domain.model.UserSchedule
import com.example.duncanclark.henrymedsproject.domain.repository.UserScheduleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserScheduleRepositoryImpl @Inject constructor(
    scheduleDataSource: ScheduleDataSource
): UserScheduleRepository {
    override fun getSchedule(user: User): Flow<Schedule> {

    }

    override fun setSchedule(
        user: User,
        schedule: UserSchedule,
        eventToSchedule: Event
    ): Flow<Result<UserSchedule>> {
        TODO("Not yet implemented")
    }

}