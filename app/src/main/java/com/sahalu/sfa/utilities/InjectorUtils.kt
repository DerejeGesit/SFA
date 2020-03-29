package com.sahalu.sfa.utilities

import android.content.Context
import androidx.fragment.app.Fragment
import com.sahalu.sfa.data.AppDatabase
import com.sahalu.sfa.data.city.CityRepository
import com.sahalu.sfa.data.outlet.OutletsRepository
import com.sahalu.sfa.data.outletnew.OutletAmedmentRepository
import com.sahalu.sfa.data.user.UserRepository
import com.sahalu.sfa.viewmodels.homepage.HomePageViewModelFactory
import com.sahalu.sfa.viewmodels.login.LoginViewModelFactory
import com.sahalu.sfa.viewmodels.outletamedmentpage.OutletAmedmentViewModel
import com.sahalu.sfa.viewmodels.outletamedmentpage.OutletAmedmentViewModelFactory
import com.sahalu.sfa.viewmodels.outletlistpage.OutletViewModelFactory

object InjectorUtils {

    private fun getUserRepository(context: Context): UserRepository {
        return UserRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).userDao()
        )
    }

    private fun getOutLetRepository(context: Context): OutletsRepository {
        return OutletsRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).outletDao()
        )
    }

    private fun getCitytRepository(context: Context): CityRepository {
        return CityRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).cityDao()
        )
    }

    private fun getOutletAmedmentRepository(context: Context):OutletAmedmentRepository
    {
        return OutletAmedmentRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).outletamedmentDao()
        )

    }
    fun provideOutletAmedmentListRModelFactory(fragment: Fragment): OutletAmedmentViewModelFactory {
        val repository = getOutletAmedmentRepository(fragment.requireContext())
        val cityRepo = getCitytRepository(fragment.requireContext())
        return OutletAmedmentViewModelFactory(
            repository,
            cityRepo,
            fragment
        )
    }
    fun provideHomePageViewModelFactory(fragment: Fragment): HomePageViewModelFactory {
        val repository = getOutLetRepository(fragment.requireContext())
        val cityRepo = getCitytRepository(fragment.requireContext())
        return HomePageViewModelFactory(
            repository,
            cityRepo,
            fragment
        )
    }
    fun provideOutletListModelFactory(fragment: Fragment): OutletViewModelFactory {
        val repository = getOutLetRepository(fragment.requireContext())
        return OutletViewModelFactory(
            repository,
            fragment
        )
    }


    fun provideLoginViewModelFactory(fragment: Fragment): LoginViewModelFactory {
        val repository = getUserRepository(fragment.requireContext())
        return LoginViewModelFactory(
            repository,
            fragment
        )
    }


}