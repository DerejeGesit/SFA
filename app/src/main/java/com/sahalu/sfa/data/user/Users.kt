package com.sahalu.sfa.data.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "users")
data class Users(
    @PrimaryKey @ColumnInfo(name = "id") @SerializedName("_id") val userId: String,
    val first_name: String,
    val last_name: String,
    val phone_number: String,
    val role_id: String,
    val date_created: String,
    val last_modified: String
) {

    override fun toString() = "$first_name $last_name"
}