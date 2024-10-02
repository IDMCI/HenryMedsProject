package com.example.duncanclark.henrymedsproject.domain.model

/**
 * Standard User object. Most likely will include more information (ie. Name, contact info, etc).
 *
 * Should create another type of User object to represent authenticated User of app.
 */
data class User(
    val id: Long,
    val userType: UserType,
)