package uz.mrsolijon.quizapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("first_name")
    val fName: String,
    @ColumnInfo("last_name")
    val lName: String,
    @ColumnInfo("user_image")
    val userImageUri: String,
    @ColumnInfo("date_of_birth")
    val dateOfBirth: String,
    val email: String,

    )
