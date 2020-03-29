package com.sahalu.sfa.data.nation

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NationsDao {
    @Query("SELECT * FROM nations ORDER BY id LIMIT 1")
    fun getNations(): LiveData<List<Nations>>

    @Query("SELECT * FROM nations where id = :nationId")
    fun getNation(nationId: String): LiveData<Nations>

//    @Query("SELECT * FROM users where phone_number = :phoneNumber")
//    fun getUserFromPhone(phoneNumber : String)

    @Insert
    suspend fun insertNations(nation: Nations): Long

    @Update
    suspend fun updateNations(nation: Nations)

    @Delete
    suspend fun deleteNations(nation: Nations)
}
