package com.sahalu.sfa.services

import com.sahalu.sfa.data.city.City
import com.sahalu.sfa.data.outlet.Outlets
import com.sahalu.sfa.utilities.RetrofitInstance
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.*

interface SyncApiService {

    @GET("/outlets")
    fun syncOutlets(@Query("last_modified" ) lastModified: String,
                    @Header("Authorization") token: String ): Call<ArrayList<Outlets>>

    @GET("/city")
    fun syncCities(@Query("last_modified" ) lastModified: String,
                 @Header("Authorization") token: String ): Call<ArrayList<City>>




    companion object {
        fun create(): SyncApiService {
            val retrofit: Retrofit? = RetrofitInstance.getInstance()
            return retrofit!!.create(SyncApiService::class.java)
        }
    }
}
