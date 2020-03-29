package com.sahalu.sfa.viewmodels.outletlistpage

import android.location.Location

import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.ViewModel

import com.sahalu.sfa.MyApplication
import com.sahalu.sfa.data.outlet.Outlets
import com.sahalu.sfa.data.outlet.OutletsRepository
import com.sahalu.sfa.location.LocationLiveData

class OutletViewModel  internal constructor(
    outletsRepository: OutletsRepository
) : ViewModel() {
   var outlets = MutableLiveData<List<Outlets>>()
   var userLoc = MutableLiveData<Location>()



    private val locationData = LocationLiveData(MyApplication.applicationContext())
    private var repo = outletsRepository
    private var allOutletsList = repo.getOutlets()
    private lateinit var allOutlets: List<Outlets>


    init{
       repo =  outletsRepository
       getOutlets()
    }





   private fun getOutlets() {
           val filterOutlets: ArrayList<Outlets> = ArrayList()

           allOutletsList.observeForever {
              if(it != null){
                  allOutlets = it
              }
           }

           locationData.observeForever { locationData ->
               filterOutlets.clear()
               val userLocation = Location("")
               userLocation.longitude = locationData.longitude
               userLocation.latitude = locationData.latitude
               userLoc.value = userLocation
               for (outlet in allOutlets) {
                   val cloneOutlet = outlet.copy()
                   val outletLocation = Location("")
                   outletLocation.longitude = cloneOutlet.lon.toDouble()
                   outletLocation.latitude = cloneOutlet.lat.toDouble()
                   cloneOutlet.distance = "${outletLocation.distanceTo(userLocation)} m"
                   if(outletLocation.distanceTo(userLocation) < 50){
                       filterOutlets.add(cloneOutlet)
                   }

               }
               outlets.value = filterOutlets
           }
    }

}