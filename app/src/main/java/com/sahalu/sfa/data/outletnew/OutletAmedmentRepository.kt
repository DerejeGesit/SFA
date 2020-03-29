package com.sahalu.sfa.data.outletnew
import androidx.lifecycle.LiveData
class OutletAmedmentRepository private constructor(private val outletAmedmentDao: OutletAmedmentDao){




    fun addOutletAmedment(outletamedment: OutletAmedments) = outletAmedmentDao.insertOutletAmedments(outletamedment)




    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: OutletAmedmentRepository? = null

        fun getInstance(plantDao: OutletAmedmentDao) =
            instance ?: synchronized(this) {
                instance
                    ?: OutletAmedmentRepository(plantDao).also { instance = it }
            }
    }
}