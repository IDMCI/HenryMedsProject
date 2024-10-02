package com.example.duncanclark.henrymedsproject.domain.model

import kotlinx.datetime.LocalDateTime

typealias Schedule = List<Event>

data class Event(
    val dateTime: LocalDateTime,
    val withUserId: Long,
    val withUserType: UserType,
)