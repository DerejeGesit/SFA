package com.sahalu.sfa.utilities

import com.google.gson.GsonBuilder
import com.sahalu.sfa.data.outlet.OutletDeserializer
import com.sahalu.sfa.data.outlet.Outlets
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitInstance {
    companion object {
        private var retrofit: Retrofit? = null
        private val interceptor = HttpLoggingInterceptor()

        private val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        var g = GsonBuilder()
            .registerTypeAdapter(Outlets::class.java,
                OutletDeserializer()
            )
            .create()

        fun getInstance(): Retrofit? {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .client(OkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create(g))
                    .build()
            }
            return retrofit
        }
    }

}