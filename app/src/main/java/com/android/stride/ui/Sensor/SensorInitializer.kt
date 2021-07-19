package com.android.stride.ui.Sensor


import android.content.Context
import android.content.Context.SENSOR_SERVICE
import android.hardware.Sensor
import android.hardware.SensorEventListener
import android.hardware.SensorManager

class SensorInitializer(application: Context) {

    //sensor we need
    var sensorManager: SensorManager = application.getSystemService(SENSOR_SERVICE) as SensorManager
    var sensor: Sensor? = null

    public fun getSensorList() : List<Sensor>{
        return sensorManager.getSensorList(Sensor.TYPE_ALL)
    }

    public fun setSensor(int: Int): Unit{
        sensor = sensorManager.getDefaultSensor(int)
    }

    public fun registerListener(sensorEventListener: SensorEventListener, rate: Int = SensorManager.SENSOR_DELAY_FASTEST ): Unit {

        sensor?.also {
            sensorManager.registerListener(
                sensorEventListener,
                it,
                rate
            )
        }

    }

    public fun unregisterListener(sensorEventListener: SensorEventListener) : Unit{
        sensorManager.unregisterListener(sensorEventListener)
    }
}