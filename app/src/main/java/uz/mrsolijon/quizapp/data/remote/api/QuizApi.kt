package uz.mrsolijon.quizapp.data.remote.api

import uz.mrsolijon.quizapp.data.remote.response.QuizResponse
import uz.mrsolijon.quizapp.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query



interface QuizApi {
    @GET("api.php")
    suspend fun getQuizzesByCategoryId(
        @Query("amount") amount: Int = Constants.QUESTIONS_AMOUNT,
        @Query("category") categoryId: Int
    ): QuizResponse

}