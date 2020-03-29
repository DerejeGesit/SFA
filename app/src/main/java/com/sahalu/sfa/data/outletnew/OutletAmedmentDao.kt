package com.sahalu.sfa.data.outletnew
import androidx.lifecycle.LiveData
import androidx.room.*

import androidx.room.Insert
@Dao
interface OutletAmedmentDao {

//     @Query("SELECT * FROM outletamedments")
//    suspend fun getOutletAmedments(): LiveData<List<OutletAmedments>>
//
//   @Query("SELECT * FROM outletamedments where ID = :outletId")
//   suspend  fun getOutletAmedments(outletId: String): LiveData<OutletAmedments>

    @Insert
    fun insertOutletAmedments(outletamedment: OutletAmedments): Long

    @Update
    suspend fun updateOutletAmedments(outlet: OutletAmedments)

    @Delete
    suspend fun deleteOutletAmedments(outlet: OutletAmedments)

}