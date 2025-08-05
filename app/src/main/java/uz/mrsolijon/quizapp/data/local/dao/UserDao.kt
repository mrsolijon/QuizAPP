package uz.mrsolijon.quizapp.data.local.dao

import uz.mrsolijon.quizapp.data.local.entity.UserEntity
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserData(userEntity: UserEntity): Long

    @Update
    suspend fun updateUserData(userEntity: UserEntity)

    @Query("SELECT * FROM user_table WHERE id=:userId")
    suspend fun getUserDataById(userId: Long): UserEntity

    @Query("SELECT EXISTS(SELECT * FROM user_table)")
    fun hasUserData(): Boolean

}