package uz.mrsolijon.quizapp.utils

import android.content.Context
import androidx.core.content.ContextCompat.getString
import uz.mrsolijon.quizapp.R


object Constants {

    const val BASE_URL = "https://opentdb.com/"

    const val QUESTIONS_AMOUNT = 20

    const val HISTORY_ID = 23

    const val GEOGRAPHY_ID = 22

    const val MATH_ID = 19

    const val SPORT_ID = 21

    const val GENERAL_KNOWLEDGE = 9

    const val TOTAL_SECONDS = 20

    const val PREFS_USER_NAME: String = "userName"
    const val PREFS_SCORE_ID = "scoreId"
    const val PREFS_USER_ID = "userId"
    const val PREFS_IS_HAVE = "isHave"

    const val WEEK_MILLISECONDS = 604800000
    const val MONTH_MILLISECONDS = 2629746000

    private fun getMonth(month: Int, context: Context): String {
        return when (month) {
            1 -> getString(context, R.string.month_january)
            2 -> getString(context, R.string.month_february)
            3 -> getString(context, R.string.month_march)
            4 -> getString(context, R.string.month_april)
            5 -> getString(context, R.string.month_may)
            6 -> getString(context, R.string.month_june)
            7 -> getString(context, R.string.month_july)
            8 -> getString(context, R.string.month_august)
            9 -> getString(context, R.string.month_september)
            10 -> getString(context, R.string.month_october)
            11 -> getString(context, R.string.month_november)
            12 -> getString(context, R.string.month_december)
            else -> {
                getString(context, R.string.month_january)
            }
        }
    }

    fun getAllTabsNames() = listOf("All time", "This week", "Month")

    fun makeDateToString(day: Int, month: Int, year: Int, context: Context): String {
        return "${getMonth(month, context)} $day $year"
    }


}