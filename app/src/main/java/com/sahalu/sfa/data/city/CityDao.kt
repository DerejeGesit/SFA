package com.sahalu.sfa.data.city

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CityDao {
    @Query("SELECT * FROM cities")
    fun getCities(): LiveData<List<City>>

    @Query("SELECT * FROM cities where id = :cityId")
    fun getCity(cityId: String): LiveData<City>

    @Insert
    suspend fun insertCity(outlet: City): Long

    @Update
    suspend fun updateCity(outlet: City)

    @Delete
    suspend fun deleteCity(outlet: City)
}
