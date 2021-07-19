package com.android.stride.data.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import com.android.stride.data.database.SensorsDatabase
import com.android.stride.data.model.AccelerometerEntity


class AccelerometerRepo(val context: Context) {

    //instantiate Dao Object
    val accelerometerDao = SensorsDatabase.getDatabase(context).getAccelerometerDao();

    fun getActivityList(): LiveData<List<AccelerometerEntity>> {
        return accelerometerDao.getActivityList()

    }

    fun getAccList() : LiveData<List<Double>>{
        return accelerometerDao.getAccList()
    }

    suspend fun insertItem(item : AccelerometerEntity){
         accelerometerDao.insertNewItem(item)
    }

    suspend fun deleteAll(){
        accelerometerDao.deleteAll()
    }
}