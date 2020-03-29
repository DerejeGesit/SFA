package com.sahalu.sfa.data

import android.content.Context

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sahalu.sfa.data.city.City
import com.sahalu.sfa.data.city.CityDao
import com.sahalu.sfa.data.company.Companies
import com.sahalu.sfa.data.nation.Nations
import com.sahalu.sfa.data.nation.NationsDao
import com.sahalu.sfa.data.outlet.Outlets
import com.sahalu.sfa.data.outlet.OutletsDao
import com.sahalu.sfa.data.outletnew.OutletAmedmentDao
import com.sahalu.sfa.data.outletnew.OutletAmedments
import com.sahalu.sfa.data.user.Users
import com.sahalu.sfa.data.user.UsersDao
import com.sahalu.sfa.utilities.DATABASE_NAME

/**
 * The Room database for this app
 */
@Database(entities = [Users::class, OutletAmedments::class, Nations::class, Companies::class, Outlets::class, City::class],
    version = 9,
    exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UsersDao
    abstract fun nationDao(): NationsDao
    abstract fun outletDao(): OutletsDao
    abstract fun outletamedmentDao(): OutletAmedmentDao
    abstract fun cityDao(): CityDao

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}
