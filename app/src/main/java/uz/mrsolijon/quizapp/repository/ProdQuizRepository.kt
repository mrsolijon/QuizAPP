package uz.mrsolijon.quizapp.repository

import uz.mrsolijon.quizapp.data.local.LocalSource
import uz.mrsolijon.quizapp.data.local.entity.ScoreEntity
import uz.mrsolijon.quizapp.data.local.entity.UserEntity
import uz.mrsolijon.quizapp.data.remote.api.QuizApi
import uz.mrsolijon.quizapp.data.remote.response.DetailedAnswerResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject



class ProdQuizRepository @Inject constructor(
    private val remoteSource: QuizApi,
    private val localSource: LocalSource
) : QuizRepository {

    override suspend fun getQuizzesByCategory(categoryId: Int): Result<List<DetailedAnswerResult>> =
        runCatching {
            withContext(Dispatchers.IO) {
                remoteSource.getQuizzesByCategoryId(categoryId = categoryId).results
            }
        }

    override suspend fun insertUserData(userEntity: UserEntity) {
        localSource.insertUserData(userEntity)
    }

    override suspend fun insertScoreData(scoreEntity: ScoreEntity) {
        localSource.insertUserData(scoreEntity)
    }

    override suspend fun updateScoreData(scoreEntity: ScoreEntity) {
        localSource.updateScoreData(scoreEntity)
    }

    override suspend fun updateUserData(userEntity: UserEntity) {
        localSource.updateUserData(userEntity)
    }

    override fun getUserDataById(): Flow<UserEntity> = localSource.getUserDataById()

    override fun getScoreDataById(): Flow<ScoreEntity> = localSource.getScoreDataById()

    override fun hasUserData(): Boolean = localSource.hasUserData()

    override fun hasScoreData(): Boolean = localSource.hasScoreData()
}