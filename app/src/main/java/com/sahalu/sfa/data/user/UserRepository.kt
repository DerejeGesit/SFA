package com.sahalu.sfa.data.user

import androidx.lifecycle.LiveData

class UserRepository private constructor(private val userDao: UsersDao) {

    fun getUsers(): LiveData<List<Users>> {
        val users = userDao.getUsers()
        return users
    }

    fun getUser(userId: String) = userDao.getUser(userId)

    suspend fun addUser(user: Users) = userDao.insertUsers(user)

    suspend fun remove(user: Users) = userDao.deleteUsers(user)

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(plantDao: UsersDao) =
            instance ?: synchronized(this) {
                instance
                    ?: UserRepository(plantDao).also { instance = it }
            }
    }
}