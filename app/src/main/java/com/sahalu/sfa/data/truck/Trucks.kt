package com.sahalu.sfa.data.truck

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "trucks")
data class Trucks(
    @PrimaryKey @ColumnInfo(name = "id") @SerializedName("_id") val truckId: String,
    val truck_code: String,
    val truck_name: String,
    val territory_id: String,
    val plate_number: String,
    val created_by: String,
    val date_created: String,
    val last_modified: String
) {

    override fun toString() = "$truck_code $truck_name"
}