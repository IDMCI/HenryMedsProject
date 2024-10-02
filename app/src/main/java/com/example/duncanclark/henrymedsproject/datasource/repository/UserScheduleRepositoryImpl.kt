package com.example.duncanclark.henrymedsproject.datasource.repository

import com.example.duncanclark.henrymedsproject.datasource.dao.UserScheduleDao
import com.example.duncanclark.henrymedsproject.datasource.mapper.StoredMapper
import com.example.duncanclark.henrymedsproject.datasource.model.Client
import com.example.duncanclark.henrymedsproject.datasource.model.Provider
import com.example.duncanclark.henrymedsproject.datasource.remote.UserScheduleDataSource
import com.example.duncanclark.henrymedsproject.domain.model.Event
import com.example.duncanclark.henrymedsproject.domain.model.User
import com.example.duncanclark.henrymedsproject.domain.model.UserSchedule
import com.example.duncanclark.henrymedsproject.domain.model.UserType
import com.example.duncanclark.henrymedsproject.domain.repository.UserScheduleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserScheduleRepositoryImpl @Inject constructor(
    private val remoteDataSource: UserScheduleDataSource,
    private val dao: UserScheduleDao,
    private val storedMapper: StoredMapper,
): UserScheduleRepository {
    // Add remoteDataSource below
    override suspend fun getSchedule(user: User): Flow<Result<UserSchedule>> = flow {
        try {
            when(user.userType) {
                UserType.PROVIDER -> {
                    dao.getProviderById(user.id)
                }
                UserType.CLIENT,
                UserType.UNKNOWN -> dao.getClientById(user.id)
            }

        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    // Add remoteDataSource below
    override suspend fun setSchedule(
        user: User,
        schedule: UserSchedule,
        eventToSchedule: Event?
    ): Flow<Result<UserSchedule>> = flow {
        val data = storedMapper(schedule, user)

        try {
            when(data) {
                is Client -> dao.insertClient(data)
                is Provider -> dao.insertProvider(data)
            }

        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}