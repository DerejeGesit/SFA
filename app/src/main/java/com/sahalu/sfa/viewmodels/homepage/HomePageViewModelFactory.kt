package com.sahalu.sfa.viewmodels.homepage

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.sahalu.sfa.data.city.CityRepository
import com.sahalu.sfa.data.outlet.OutletsRepository
import com.sahalu.sfa.data.user.UserRepository
import com.sahalu.sfa.viewmodels.login.LoginViewModel

class HomePageViewModelFactory(
    private val repository: OutletsRepository,
    private val cityRepo: CityRepository,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return HomePageViewModel(repository, cityRepo) as T
    }
}