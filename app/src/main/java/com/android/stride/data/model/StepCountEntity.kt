package com.android.stride.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "StepCountTable")
data class StepCountEntity(
        @PrimaryKey
        @ColumnInfo(name = "Date")
        val date: String,
        @ColumnInfo(name = "Total_Steps")
        val stepCount : Int)
