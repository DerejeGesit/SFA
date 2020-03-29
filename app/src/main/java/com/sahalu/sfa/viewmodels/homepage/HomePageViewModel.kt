package com.sahalu.sfa.viewmodels.homepage

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pixplicity.easyprefs.library.Prefs
import com.sahalu.sfa.data.city.City
import com.sahalu.sfa.data.city.CityRepository
import com.sahalu.sfa.data.outlet.Outlets
import com.sahalu.sfa.data.outlet.OutletsRepository
import com.sahalu.sfa.services.SyncApiService
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePageViewModel  internal constructor(
    outletsRepository: OutletsRepository,
    cityRepo: CityRepository
) : ViewModel() {

   private val outletsSync  = MutableLiveData<Boolean>()
   private val citySync = MutableLiveData<Boolean>()
    val ntwError = MutableLiveData<Boolean>()
    val syncComplete = MutableLiveData<Boolean>()
    val outRep = outletsRepository
    val cityRep = cityRepo

    init {
        outletsSync.value = false
        citySync.value = false
        ntwError.value = false
        syncComplete.value = false

    }

    fun sync() {
        if(!outletsSync.value!!){
           val outletLatestDate = Prefs.getString("outlet", "2010-02-11T07:16:29.044Z")
            val citiesLatestDate = Prefs.getString("cities", "2010-02-11T07:16:29.044Z")
           val token = Prefs.getString("token", "NULL")
            val syncApi = SyncApiService.create();
            syncApi.syncOutlets(outletLatestDate, "Bearer $token" ).enqueue(object :Callback<ArrayList<Outlets>>{
                override fun onFailure(call: Call<ArrayList<Outlets>>, t: Throwable) {
                   ntwError.value = true
                }

                override fun onResponse(
                    call: Call<ArrayList<Outlets>>,
                    response: Response<ArrayList<Outlets>>
                ) {
                    if (response.body() != null) {

                        val outlets = response.body()!!
                        for(outlet in outlets) {
                            runBlocking {
                              val long =   outRep.addOutlet(outlet)
                              Log.d("Created outline", long.toString())
                            }
                            Prefs.putString("outlet", outlet.last_modified)
                        }
                        outletsSync.value = true
                        if(citySync.value!!){
                            syncComplete.value = true
                        }

                    } else {
                        ntwError.value = true
                    }

                }
            })

            syncApi.syncCities(citiesLatestDate, "Bearer $token").enqueue(object :Callback<ArrayList<City>>{
                override fun onFailure(call: Call<ArrayList<City>>, t: Throwable) {
                    ntwError.value = true
                }

                override fun onResponse(
                    call: Call<ArrayList<City>>,
                    response: Response<ArrayList<City>>
                ) {
                    if (response.body() != null) {

                        val cities = response.body()!!
                        for(city in cities) {
                            runBlocking {
                                val long =  cityRep.addCity(city)
                                Log.d("Created City", long.toString())
                            }
                            Prefs.putString("cities", city.last_modified)
                        }
                        citySync.value = true
                        if(outletsSync.value!!){
                            syncComplete.value = true
                        }

                    } else {
                        ntwError.value = true
                    }

                }
            })
        }
    }

}