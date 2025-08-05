package uz.mrsolijon.quizapp.repository

import uz.mrsolijon.quizapp.data.local.entity.ScoreEntity
import uz.mrsolijon.quizapp.data.local.entity.UserEntity
import uz.mrsolijon.quizapp.data.remote.response.DetailedAnswerResult
import kotlinx.coroutines.flow.Flow



interface QuizRepository {

    suspend fun getQuizzesByCategory(categoryId: Int): Result<List<DetailedAnswerResult>>

    suspend fun insertUserData(userEntity: UserEntity)

    suspend fun insertScoreData(scoreEntity: ScoreEntity)

    suspend fun updateScoreData(scoreEntity: ScoreEntity)

    suspend fun updateUserData(userEntity: UserEntity)

    fun getUserDataById(): Flow<UserEntity>

    fun getScoreDataById(): Flow<ScoreEntity>

    fun hasUserData(): Boolean

    fun hasScoreData(): Boolean
}