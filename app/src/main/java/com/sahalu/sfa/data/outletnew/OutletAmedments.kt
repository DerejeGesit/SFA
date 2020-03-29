package com.sahalu.sfa.data.outletnew

import androidx.room.*
import com.google.gson.annotations.SerializedName
import com.sahalu.sfa.data.city.City
import com.sahalu.sfa.data.company.Companies
import com.sahalu.sfa.data.nation.Nations
import com.sahalu.sfa.data.outlet.Outlets
import com.sahalu.sfa.data.truck.Trucks
import com.sahalu.sfa.data.user.Users
import java.util.*

@Entity(
    tableName = "outletamedments"
)

data class OutletAmedments(
    @PrimaryKey @ColumnInfo(name = "id") @SerializedName("_id") val outletId: String,

    val outlet_name: String,
    val owner_name: String,
    val phone_number: String,
    val requested_by: String,
    val company_id: String,
    val city_id: String,
    val truck_id: String,
    val approved_by: String,

    //val geo_location: GeoLocation,
    var lon: String,
    var lat: String,
    val requested_date: String,
    val approved_date: String,
    val reason: String,
    val rejected: Boolean,
    val created_by: String,
    val status_control: Boolean,
    val date_created: String,
    val last_modified: String
    //val date_created: Date,
    //val last_modified: Date
) {

    override fun toString() = "$outlet_name $owner_name $phone_number " +
            "$requested_by $company_id $city_id " +
            "$truck_id $approved_by $lon $lat $requested_date $approved_date " +// $geo_location
            "$reason $rejected $created_by " +
            "$truck_id $approved_by $status_control"
}