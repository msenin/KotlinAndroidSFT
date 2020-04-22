package ru.senin.kotlin.android.activityapplication.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Messages",
    foreignKeys = [
        ForeignKey(entity = User::class,
                   parentColumns = arrayOf("login"),
                   childColumns = arrayOf("fromUser"),
                   onDelete = ForeignKey.RESTRICT),
        ForeignKey(entity = User::class,
                   parentColumns = arrayOf("login"),
                   childColumns = arrayOf("toUser"),
                   onDelete = ForeignKey.RESTRICT)
])
data class Message (
    @ColumnInfo(name = "fromUser")
    val from: String,
    @ColumnInfo(name = "toUser")
    val to: String,
    val body: String,
    val timestamp: Long
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}