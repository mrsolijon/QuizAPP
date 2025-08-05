package uz.mrsolijon.quizapp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.mrsolijon.quizapp.data.local.QuizDatabase
import uz.mrsolijon.quizapp.data.local.dao.ScoreDao
import uz.mrsolijon.quizapp.data.local.dao.UserDao
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideQuizDatabase(@ApplicationContext context: Context): QuizDatabase =
        Room.databaseBuilder(context, QuizDatabase::class.java, "quiz_database").build()

    @Singleton
    @Provides
    fun provideScoreDao(database: QuizDatabase): ScoreDao = database.scoreDao()

    @Singleton
    @Provides
    fun provideUserDao(database: QuizDatabase): UserDao = database.userDao()

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences("quiz_preference", Context.MODE_PRIVATE)

}