package com.sahalu.sfa.viewmodels.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pixplicity.easyprefs.library.Prefs
import com.sahalu.sfa.data.user.UserRepository
import com.sahalu.sfa.data.user.Users
import com.sahalu.sfa.services.LoginApiService
import com.sahalu.sfa.services.LoginPostData
import com.sahalu.sfa.services.LoginResponse
import kotlinx.coroutines.runBlocking
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginViewModel internal constructor(
    userRepository: UserRepository
) : ViewModel() {
    enum class AuthenticationState {
        UNAUTHENTICATED,        // Initial state, the user needs to authenticate
        AUTHENTICATED,        // The user has authenticated successfully
        INVALID_AUTHENTICATION  // Authentication failed
    }

    val authenticationState = MutableLiveData<AuthenticationState>()
    var user: LiveData<List<Users>> = userRepository.getUsers()
    val userRep = userRepository
    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()


    init {
        loading.value = false
        errorMessage.value = ""
        user.observeForever { user ->
            if (user !== null && user.isNotEmpty())
                authenticationState.value =
                    AuthenticationState.AUTHENTICATED
            else
                authenticationState.value =
                    AuthenticationState.UNAUTHENTICATED
        }

    }


    fun refuseAuthentication() {
        authenticationState.value =
            AuthenticationState.UNAUTHENTICATED
    }

    fun authenticate(phoneNumber: String, password: String) {
        errorMessage.value = ""
        passwordIsValidForUsername(phoneNumber, password)
    }

    fun logout() {
        runBlocking {
            if(user.value?.size!! > 0){
                userRep.remove(user.value!![0])
            }
        }
        authenticationState.value =
            AuthenticationState.INVALID_AUTHENTICATION
    }

    private fun passwordIsValidForUsername(phoneNumber: String, password: String) {
        Log.d("Server", "Call server here $phoneNumber $password")
        loading.value = true
        doAsync {
            LoginApiService.create().dologin(LoginPostData(phoneNumber, password))
                .enqueue(object : Callback<LoginResponse> {
                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Log.v("retrofit", "failed", t)
                        authenticationState.value =
                            AuthenticationState.INVALID_AUTHENTICATION
                        loading.value = false
                        errorMessage.value = "Can't connect to internet"
                    }

                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        //  data.value = response!!.body()!!.articles
                        Log.v("retrofit", "call success")
                        if (response.body() != null) {
                            Prefs.putString("token", response.body()!!.token)
                            runBlocking {
                                userRep.addUser(response.body()!!.user)
                            }
                            authenticationState.value =
                                AuthenticationState.AUTHENTICATED
                            loading.value = false


                        } else {
                             authenticationState.value =
                                 AuthenticationState.INVALID_AUTHENTICATION
                             errorMessage.value = "Wrong phone number or password"
                             loading.value = false
                        }
                    }

                })


        }
    }
}