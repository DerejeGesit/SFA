package com.sahalu.sfa.data.outlet

import android.util.Log
import androidx.lifecycle.LiveData

class OutletsRepository private constructor(private val outletsDao: OutletsDao) {

    fun getOutlets(): LiveData<List<Outlets>> {
        val outlets =  outletsDao.getOutlets()
        Log.d("outlets", outlets.value.toString())
        return outlets
    }

    fun getOutlet(outletId: String) = outletsDao.getOutlet(outletId)

    suspend fun addOutlet(outlet: Outlets) = outletsDao.insertOutlets(outlet)

    suspend fun remove(outlet: Outlets) = outletsDao.deleteOutlets(outlet)

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: OutletsRepository? = null

        fun getInstance(plantDao: OutletsDao) =
            instance ?: synchronized(this) {
                instance
                    ?: OutletsRepository(plantDao).also { instance = it }
            }
    }
}