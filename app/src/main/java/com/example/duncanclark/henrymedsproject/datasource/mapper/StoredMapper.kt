package com.example.duncanclark.henrymedsproject.datasource.mapper

import com.example.duncanclark.henrymedsproject.datasource.model.Client
import com.example.duncanclark.henrymedsproject.datasource.model.Provider
import com.example.duncanclark.henrymedsproject.datasource.model.StoredUser
import com.example.duncanclark.henrymedsproject.domain.model.User
import com.example.duncanclark.henrymedsproject.domain.model.UserSchedule
import com.example.duncanclark.henrymedsproject.domain.model.UserType
import javax.inject.Inject

class StoredMapper @Inject constructor() {

    operator fun invoke(
        userSchedule: UserSchedule,
        user: User
    ): StoredUser {
        return when (user.userType) {
            UserType.PROVIDER -> providerToStoredUser(userSchedule, user)
            UserType.CLIENT,
            UserType.UNKNOWN -> clientToStoredUser(userSchedule, user)
        }
    }

    private fun clientToStoredUser(userSchedule: UserSchedule, user: User): StoredUser {
        return Client(
            id = userSchedule.userId,
            user = user,
            userSchedule = userSchedule
        )
    }

    private fun providerToStoredUser(userSchedule: UserSchedule, user: User): StoredUser {
        return Provider(
            id = userSchedule.userId,
            user = user,
            userSchedule = userSchedule
        )
    }
}