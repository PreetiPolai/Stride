package com.android.stride.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.stride.data.daoInterfaces.AccelerometerDao
import com.android.stride.data.daoInterfaces.StepCountDao
import com.android.stride.data.model.AccelerometerEntity
import com.android.stride.data.model.StepCountEntity

@Database(entities = [StepCountEntity::class, AccelerometerEntity :: class], version = 5)
abstract class SensorsDatabase : RoomDatabase() {

    //get the step count dao
    abstract fun getStepCountDao() : StepCountDao

    //get the accelerometer dao
    abstract fun getAccelerometerDao() : AccelerometerDao



    //singletonPattern to instantiate database object
    companion object{
       private var instance : SensorsDatabase? = null
        fun getDatabase(context: Context) = instance ?: synchronized(this){
            Room.databaseBuilder(
                context.applicationContext, SensorsDatabase :: class.java, "SensorDatabase"
            ).fallbackToDestructiveMigration()
                    .build()
                    .also {
                instance = it
            }
        }
    }




}