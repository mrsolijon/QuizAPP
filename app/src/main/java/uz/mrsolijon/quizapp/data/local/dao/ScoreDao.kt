package uz.mrsolijon.quizapp.data.local.dao

import uz.mrsolijon.quizapp.data.local.entity.ScoreEntity
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update



@Dao
interface ScoreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertScoreData(scoreEntity: ScoreEntity): Long

    @Update
    suspend fun updateScoreData(scoreEntity: ScoreEntity)

    @Query("SELECT * FROM score_table WHERE id=:scoreId")
    suspend fun getScoreDataById(scoreId: Long): ScoreEntity

    @Query("SELECT EXISTS(SELECT * FROM score_table)")
    fun hasScoreData(): Boolean

}