package com.sahalu.sfa.data.outletnew

import androidx.room.ColumnInfo
import androidx.room.TypeConverter
import com.google.gson.annotations.SerializedName


data class GeoLocation (

    var lat: String,

    var lon: String)