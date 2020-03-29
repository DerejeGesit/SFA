package com.sahalu.sfa.data.company

import androidx.lifecycle.LiveData


class CompanyRepository private constructor(private val companyDoa: CompaniesDao) {

    fun getCompanies(): LiveData<List<Companies>> {
        return companyDoa.getCompanies()
    }

    fun getNation(companyId: String) = companyDoa.getNation(companyId)

    suspend fun addNation(company: Companies) = companyDoa.insertCompanies(company)

    suspend fun remove(company: Companies) = companyDoa.deleteCompanies(company)

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: CompanyRepository? = null

        fun getInstance(companyDoa: CompaniesDao) =
            instance ?: synchronized(this) {
                instance
                    ?: CompanyRepository(companyDoa).also { instance = it }
            }
    }
}