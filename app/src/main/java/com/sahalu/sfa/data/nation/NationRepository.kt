package com.sahalu.sfa.data.nation

import androidx.lifecycle.LiveData


class NationRepository private constructor(private val nationDao: NationsDao) {

    fun getNations(): LiveData<List<Nations>> {
        val users = nationDao.getNations()
        return users
    }

    fun getNation(nationId: String) = nationDao.getNation(nationId)

    suspend fun addNation(nation: Nations) = nationDao.insertNations(nation)

    suspend fun remove(nation: Nations) = nationDao.deleteNations(nation)

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: NationRepository? = null

        fun getInstance(plantDao: NationsDao) =
            instance ?: synchronized(this) {
                instance
                    ?: NationRepository(plantDao).also { instance = it }
            }
    }
}