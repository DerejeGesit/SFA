package com.sahalu.sfa.data.outlet

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface OutletsDao {
    @Query("SELECT * FROM outlets")
    fun getOutlets(): LiveData<List<Outlets>>

    @Query("SELECT * FROM outlets where id = :outletId")
    fun getOutlet(outletId: String): LiveData<Outlets>

//    @Query("SELECT * FROM outlets where phone_number = :phoneNumber")
//    fun getOutletFromPhone(phoneNumber : String)

    @Insert
    suspend fun insertOutlets(outlet: Outlets): Long

    @Update
    suspend fun updateOutlets(outlet: Outlets)

    @Delete
    suspend fun deleteOutlets(outlet: Outlets)
}
