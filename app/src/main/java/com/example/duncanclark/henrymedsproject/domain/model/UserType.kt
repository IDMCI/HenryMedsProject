package com.example.duncanclark.henrymedsproject.domain.model

/**
 * Enum to hold the type of User.
 *
 * It would be helpful to know the [UserType] in cases of different UI and features.
 */
enum class UserType(val type: String) {
    PROVIDER("provider"),
    CLIENT("client"),
    UNKNOWN("unknown_user")
}