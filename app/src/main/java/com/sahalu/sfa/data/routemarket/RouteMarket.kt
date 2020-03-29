package com.sahalu.sfa.data.routemarket

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "routemarkets")
data class RouteMarket(
    @PrimaryKey @ColumnInfo(name = "id") @SerializedName("_id") val routeMarketId: String,
    val nation_code: String,
    val nation_name: String,
    val created_by: String,
    val date_created: String,
    val last_modified: String
) {

    override fun toString() = "$nation_code $nation_name"
}