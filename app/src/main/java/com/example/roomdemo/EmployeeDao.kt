package com.example.roomdemo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface EmployeeDao {
    @Insert
    suspend fun insert(employeeEntity: EmployeeEntity)

     @Update
    suspend fun update(employeeEntity: EmployeeEntity)

    @Delete
    suspend fun delete(employeeEntity: EmployeeEntity)

   // Flow is a part of kotlin coroutines used to hold values that can always change at runtime
    @Query("SELECT * FROM `employee-table`")
    fun fetchAllEmployees():Flow<List<EmployeeEntity>>

    @Query("SELECT * FROM `employee-table` where id=:id")
    fun fetchEmployeeById(id:Int):Flow<EmployeeEntity>


}