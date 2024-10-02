package com.example.duncanclark.henrymedsproject.domain.usecase

import com.example.duncanclark.henrymedsproject.domain.model.User
import com.example.duncanclark.henrymedsproject.domain.model.UserSchedule
import com.example.duncanclark.henrymedsproject.domain.repository.UserScheduleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ExampleGetScheduleUseCase {
    suspend fun execute(
        user: User,
    ): Flow<Result<UserSchedule>>
}

class ExampleGetScheduleUseCaseImpl @Inject constructor(
    private val userScheduleRepository: UserScheduleRepository,
): ExampleGetScheduleUseCase {
    override suspend fun execute(
        user: User,
    ): Flow<Result<UserSchedule>> {
        // Add additional business rules here.
        return userScheduleRepository.getSchedule(user)
    }
}