package com.sahalu.sfa.viewmodels.outletamedmentpage

import com.sahalu.sfa.data.city.CityRepository
import com.sahalu.sfa.data.outlet.OutletsRepository
import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.sahalu.sfa.data.outletnew.OutletAmedmentRepository

class OutletAmedmentViewModelFactory (
    private val repository: OutletAmedmentRepository,
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
            return OutletAmedmentViewModel(repository, cityRepo) as T
        }
}