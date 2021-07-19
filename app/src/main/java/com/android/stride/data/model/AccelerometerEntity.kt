package com.android.stride.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "AccelerometerRecord")
data class AccelerometerEntity(
        @ColumnInfo(name = "Date")
        val date: String,

        val totalAcc: Double,
        val totalMag: Double,
        val totalGyro: Double,

        @PrimaryKey
        val timestamp: Long,
        val currentTime: String

)
