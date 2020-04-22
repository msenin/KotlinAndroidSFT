package ru.senin.kotlin.android.activityapplication.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
data class User (
    @PrimaryKey
    val login: String,
    val name: String
)