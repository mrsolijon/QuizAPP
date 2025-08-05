package uz.mrsolijon.quizapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "score_table")
data class ScoreEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val score: Int = 0,
    @ColumnInfo("user_id")
    val userId: Int,
    val date: Long
)
