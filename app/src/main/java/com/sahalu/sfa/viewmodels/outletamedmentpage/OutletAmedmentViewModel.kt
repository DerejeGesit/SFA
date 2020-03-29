package com.sahalu.sfa.viewmodels.outletamedmentpage
import android.location.Location
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sahalu.sfa.MyApplication
import com.sahalu.sfa.data.city.City
import com.sahalu.sfa.data.city.CityRepository
import com.sahalu.sfa.data.outlet.Outlets
import com.sahalu.sfa.data.outletnew.GeoLocation
import com.sahalu.sfa.data.outletnew.OutletAmedmentRepository
import com.sahalu.sfa.location.LocationLiveData

class OutletAmedmentViewModel internal constructor(
    outletamedmentRepo: OutletAmedmentRepository,
    cityRepo: CityRepository

) : ViewModel() {

    var outloc = GeoLocation("","")
    private val locationData = LocationLiveData(MyApplication.applicationContext())

    var outletamed = outletamedmentRepo
    private var citysrepo = cityRepo
    private var allCityList = citysrepo.getCities()
    init{
        outletamed =  outletamedmentRepo
        citysrepo= cityRepo
        getCities()
    }

    fun getCities(): List<City> {
        var CityList: List<City> = emptyList()
        allCityList.observeForever {
            if (it != null) {
                CityList = it
            }
        }

        locationData.observeForever { locationData ->
            val userLocation = Location("")
            outloc.lat = locationData.longitude.toString()
            outloc.lon = locationData.latitude.toString()
        }
        return CityList
    }
    }