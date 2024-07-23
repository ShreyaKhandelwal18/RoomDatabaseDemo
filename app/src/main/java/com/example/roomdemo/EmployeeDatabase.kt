package com.example.roomdemo


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized


@Database(entities = [EmployeeEntity::class], version = 1)
abstract class EmployeeDatabase :RoomDatabase() {


    abstract fun employeeDao():EmployeeDao
     //This will keep a reference of any database returned via var Instance
    // This will help us To avoid repeatedly initializing database which is expensive in terms of performance
    //The value of volatile variable never be cached and all writes and read will be done from main memory
    //changes made by one thread is visible to all thread by using volatile

companion object
    {
        @Volatile
        private var INSTANCE: EmployeeDatabase? = null


        @OptIn(InternalCoroutinesApi::class)
        fun getInstance(context: Context): EmployeeDatabase {

            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        EmployeeDatabase::class.java,
                        "employee_database"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }

    }


}

