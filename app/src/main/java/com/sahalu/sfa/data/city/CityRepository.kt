package com.sahalu.sfa.data.city

import androidx.lifecycle.LiveData

class CityRepository private constructor(private val cityDao: CityDao) {

    fun getCities(): LiveData<List<City>> {
        return cityDao.getCities()

    }

    fun getCity(cityId: String) = cityDao.getCity(cityId)

    suspend fun addCity(outlet: City) = cityDao.insertCity(outlet)

    suspend fun remove(outlet: City) = cityDao.deleteCity(outlet)

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: CityRepository? = null

        fun getInstance(cityDao: CityDao) =
            instance ?: synchronized(this) {
                instance
                    ?: CityRepository(cityDao).also { instance = it }
            }
    }
}