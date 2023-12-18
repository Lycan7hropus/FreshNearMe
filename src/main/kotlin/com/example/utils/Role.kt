package com.example.utils

enum class Role(val roleStr: String) {
    ADMIN("realm-admin"),
    MODERATOR("realm-moderator"),
    USER("realm-user");

    companion object {
        fun fromRoleStr(roleStr: String): Role? {
            return entries.firstOrNull { it.roleStr == roleStr }
        }
    }
}
