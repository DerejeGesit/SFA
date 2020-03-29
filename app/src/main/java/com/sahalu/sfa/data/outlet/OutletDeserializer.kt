package com.sahalu.sfa.data.outlet

import com.google.gson.*
import java.lang.reflect.Type


class OutletDeserializer: JsonDeserializer<Outlets> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Outlets {
        val city = json!!.asJsonObject["city_id"]
        val cityId: String
        cityId = if (city is JsonObject) {
            city.asJsonObject["_id"].asString
        } else ""

        val geoLocation = json!!.asJsonObject["geo_location"]
        val lat = geoLocation.asJsonObject["lat"]
        val long = geoLocation.asJsonObject["long"]

        val g = GsonBuilder().create()
        val entity: Outlets = g.fromJson(json, Outlets::class.java)
        entity.city_id = cityId
        entity.lat = lat.asString
        entity.lon = long.asString

        return entity
    }
}