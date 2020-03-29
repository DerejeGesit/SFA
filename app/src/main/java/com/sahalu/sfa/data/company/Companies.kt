package com.sahalu.sfa.data.company

import androidx.room.*
import com.google.gson.annotations.SerializedName
import com.sahalu.sfa.data.nation.Nations

@Entity(tableName = "companies",  foreignKeys = [
    ForeignKey(entity = Nations::class, parentColumns = ["id"], childColumns = ["nation_id"])
],
    indices = [Index("nation_id")])
data class Companies(
    @PrimaryKey @ColumnInfo(name = "id") @SerializedName("_id") val companyId: String,
    val nation_id: String,
    val company_code: String,
    val company_name: String,
    val city: String,
    val address: String,
    val number_of_agents: Int,
    val created_by: String,
    val date_created: String,
    val last_modified: String
) {

    override fun toString() = "$company_code $company_name"
}