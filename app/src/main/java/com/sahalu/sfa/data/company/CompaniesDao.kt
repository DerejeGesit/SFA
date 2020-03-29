package com.sahalu.sfa.data.company

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


interface CompaniesDao {
    @Query("SELECT * FROM companies ORDER BY id LIMIT 1")
    fun getCompanies(): LiveData<List<Companies>>

    @Query("SELECT * FROM companies where id = :companyId")
    fun getNation(companyId: String): LiveData<Companies>

//    @Query("SELECT * FROM users where phone_number = :phoneNumber")
//    fun getUserFromPhone(phoneNumber : String)

    @Insert
    suspend fun insertCompanies(company: Companies): Long

    @Update
    suspend fun updateCompanies(company: Companies)

    @Delete
    suspend fun deleteCompanies(company: Companies)
}
