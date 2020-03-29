package com.sahalu.sfa.data.city

import androidx.room.*
import com.google.gson.annotations.SerializedName
import com.sahalu.sfa.data.company.Companies

@Entity(tableName = "cities")
data class City(
    @PrimaryKey @ColumnInfo(name = "id") @SerializedName("_id") val cityId: String,
    val nationId: String,
    val city: String,
    val sub_city: String,
    val status_control: String,
    val specific_area: String,
    val date_created: String,
    val last_modified: String
) {

    override fun toString() = "$city $sub_city $specific_area"
}