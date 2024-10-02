package com.example.duncanclark.henrymedsproject.domain.model

enum class UserType(val type: String) {
    PROVIDER("provider"),
    CLIENT("client"),
    UNKNOWN("unknown_user")
}