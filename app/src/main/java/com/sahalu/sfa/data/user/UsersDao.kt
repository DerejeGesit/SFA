package com.sahalu.sfa.data.user

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sahalu.sfa.data.user.Users

@Dao
interface UsersDao {
    @Query("SELECT * FROM users ORDER BY id LIMIT 1")
    fun getUsers(): LiveData<List<Users>>

    @Query("SELECT * FROM users where id = :userId")
    fun getUser(userId: String): LiveData<Users>

//    @Query("SELECT * FROM users where phone_number = :phoneNumber")
//    fun getUserFromPhone(phoneNumber : String)

    @Insert
    suspend fun insertUsers(user: Users): Long

    @Update
    suspend fun updateUsers(user: Users)

    @Delete
    suspend fun deleteUsers(user: Users)
}
