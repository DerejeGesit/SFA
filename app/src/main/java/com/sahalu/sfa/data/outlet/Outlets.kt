package com.sahalu.sfa.data.outlet

import androidx.room.*
import com.google.gson.annotations.SerializedName
import com.sahalu.sfa.data.city.City
import com.sahalu.sfa.data.company.Companies
import com.sahalu.sfa.data.nation.Nations
import com.sahalu.sfa.data.truck.Trucks

@Entity(tableName = "outlets")
data class Outlets(
    @PrimaryKey @ColumnInfo(name = "id") @SerializedName("_id") var outletId: String,
    var nation_id: String,
    var outlet_name: String,
    var owner_name: String,
    var company_id: String,
    var truck_id: String,
    var phone_number: String,
    var city_id: String,
    var lat: String,
    @ColumnInfo(name = "lon") var lon: String,
    var created_by: String,
    var date_created: String,
    var last_modified: String,
    @Ignore
    var distance: String?
)
{
    override fun toString() = outlet_name

    constructor() :
            this("", "", "","", "",
        "", "", "", "","","","",
        "", null)
}
