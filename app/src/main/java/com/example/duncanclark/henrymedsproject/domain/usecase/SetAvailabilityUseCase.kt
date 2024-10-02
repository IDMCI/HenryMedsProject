package com.example.duncanclark.henrymedsproject.domain.usecase

import com.example.duncanclark.henrymedsproject.domain.model.Event
import com.example.duncanclark.henrymedsproject.domain.model.User
import com.example.duncanclark.henrymedsproject.domain.model.UserSchedule
import com.example.duncanclark.henrymedsproject.domain.repository.UserScheduleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


interface SetAvailabilityUseCase {
    suspend fun execute(
        user: User,
        schedule: UserSchedule,
        eventToSchedule: Event?
    ): Flow<Result<UserSchedule>>
}

class SetAvailabilityUseCaseImpl @Inject constructor(
    private val userScheduleRepository: UserScheduleRepository,
): SetAvailabilityUseCase {
    override suspend fun execute(
        user: User,
        schedule: UserSchedule,
        eventToSchedule: Event?
    ): Flow<Result<UserSchedule>> {
        return userScheduleRepository.setSchedule(user, schedule, eventToSchedule)
    }
}