package com.sahalu.sfa.services


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.sahalu.sfa.data.user.Users
import com.sahalu.sfa.utilities.RetrofitInstance
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface LoginApiService {

    @Headers("Content-Type: application/json")
    @POST("/auth/login")
    fun dologin(@Body loginPostData: LoginPostData): Call<LoginResponse>


    companion object {
        fun create(): LoginApiService {
            val retrofit: Retrofit? = RetrofitInstance.getInstance()
            return retrofit!!.create(LoginApiService::class.java)
        }
    }
}

data class LoginPostData(
    @SerializedName("phone_number") var phone_number: String,
    @SerializedName("password") var password: String
)

data class LoginResponse(
    @SerializedName("status") val status: String = "0",
    @SerializedName("token") val token: String = "",
    @SerializedName("user") var user: Users,
    @SerializedName("error") val error: String,


    @Expose(deserialize = false) // deserialize is this filed is not required
    @SerializedName("message") val message: String = ""
)