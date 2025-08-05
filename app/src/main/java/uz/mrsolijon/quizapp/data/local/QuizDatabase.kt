package uz.mrsolijon.quizapp.data.local

import uz.mrsolijon.quizapp.data.local.dao.ScoreDao
import uz.mrsolijon.quizapp.data.local.dao.UserDao
import uz.mrsolijon.quizapp.data.local.entity.ScoreEntity
import uz.mrsolijon.quizapp.data.local.entity.UserEntity
import androidx.room.Database
import androidx.room.RoomDatabase



@Database(entities = [UserEntity::class, ScoreEntity::class], version = 1, exportSchema = false)
abstract class QuizDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun scoreDao(): ScoreDao
}