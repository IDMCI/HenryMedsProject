package com.example.duncanclark.henrymedsproject.domain.model

/**
 * @param userId
 * @param schedule - User's existing appointment schedule
 * @param availabilitySchedule - User's availability schedule.
 *
 * Discussions and documentation should happen around whether this availabilitySchedule
 * is inclusive or exclusive.
 *
 * Examples:
 * -- Exclusion approach: Holidays, Weekends, Times <= 9am, Times >= 5pm, etc.
 * -- Inclusion approach: M-F, Individual days, Times between 9am - 5pm
 */
data class UserSchedule(
    val userId: Long,
    val schedule: Schedule,
    val availabilitySchedule: Schedule,
)