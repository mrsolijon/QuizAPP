package uz.mrsolijon.quizapp.data.model

import java.io.Serializable


data class GameScoreData(
    val correctAnswersCount: Int,
    val incorrectAnswersCount: Int,
    val category: String
) : Serializable
