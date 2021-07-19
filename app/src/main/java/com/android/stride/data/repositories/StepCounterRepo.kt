package com.android.stride.data.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import com.android.stride.data.database.SensorsDatabase
import com.android.stride.data.model.StepCountEntity

class StepCounterRepo(val context : Context) {

    //instantiate Dao Object
    val stepCountDao = SensorsDatabase.getDatabase(context).getStepCountDao();

     fun getList(): LiveData<List<StepCountEntity>>{
         return stepCountDao.getStepList()
     }

    suspend fun insertItem(item : StepCountEntity) {
         stepCountDao.insertStepCount(item)
    }

    suspend fun deleteAll(){
        stepCountDao.deleteStepCount()
    }

    suspend fun update(item: StepCountEntity){
       return stepCountDao.updateStepCount(item)
    }

    fun getStepToday(date : String) : LiveData<StepCountEntity>{
        return stepCountDao.getStepToday(date)
    }

}