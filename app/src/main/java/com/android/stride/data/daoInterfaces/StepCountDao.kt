package com.android.stride.data.daoInterfaces

import android.os.FileObserver.DELETE
import androidx.lifecycle.LiveData
import androidx.room.*
import com.android.stride.data.model.StepCountEntity

@Dao
interface StepCountDao {

    @Query("SELECT * FROM StepCountTable")
    fun getStepList() : LiveData<List<StepCountEntity>>

    @Query("SELECT * FROM StepCountTable where Date = :date")
    fun getStepToday(date : String) : LiveData<StepCountEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertStepCount(item: StepCountEntity)

    @Query("DELETE FROM StepCountTable")
    suspend fun deleteStepCount()

    @Update
    suspend fun updateStepCount(item : StepCountEntity)
}