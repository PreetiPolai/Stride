package com.android.stride.data.daoInterfaces

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.stride.data.model.AccelerometerEntity


@Dao
interface AccelerometerDao {

    @Query("SELECT * FROM AccelerometerRecord")
    fun getActivityList() : LiveData<List<AccelerometerEntity>>


    @Query("SELECT totalAcc FROM AccelerometerRecord")
    fun getAccList() : LiveData<List<Double>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewItem(item : AccelerometerEntity)

    @Query("DELETE FROM AccelerometerRecord")
    suspend fun deleteAll()

}