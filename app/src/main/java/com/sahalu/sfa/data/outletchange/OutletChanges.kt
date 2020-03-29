package com.sahalu.sfa.data.outletchange

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.sahalu.sfa.data.city.City
import com.sahalu.sfa.data.company.Companies
import com.sahalu.sfa.data.nation.Nations
import com.sahalu.sfa.data.outlet.Outlets
import com.sahalu.sfa.data.truck.Trucks


@Entity(tableName = "outletchanges",  foreignKeys = [
    ForeignKey(entity = Nations::class, parentColumns = ["nationId"], childColumns = ["nation_id"]),
    ForeignKey(entity = Companies::class, parentColumns = ["companyId"], childColumns = ["company_id"]),
    ForeignKey(entity = Trucks::class, parentColumns = ["truckId"], childColumns = ["truck_id"]),
    ForeignKey(entity = City::class, parentColumns = ["cityId"], childColumns = ["city_id"] ),
    ForeignKey(entity = Outlets::class, parentColumns = ["outletId"], childColumns = ["outlet_id"])
])
data class OutletChanges(
    @PrimaryKey @ColumnInfo(name = "id") @SerializedName("_id") val outletId: String,
    val nation_id: String,
    val outlet_name: String,
    val company_id: String,
    val truck_id: String,
    val outlet_id: String,
    val phone_number: String,
    val requested_by: String,
    val approved_by: String,
    val reason: String,
    val city_id: String,
    val created_by: String,
    val date_created: String,
    val last_modified: String
) {

    override fun toString() = outlet_name
}