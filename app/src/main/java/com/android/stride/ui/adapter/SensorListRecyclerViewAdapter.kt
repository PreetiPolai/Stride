package com.android.stride.ui.adapter

import android.hardware.Sensor
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.stride.databinding.SensorlistitemBinding

class SensorListRecyclerViewAdapter(List: List<Sensor>) :
    RecyclerView.Adapter<SensorListRecyclerViewAdapter.ViewHolder>() {

    private var list = List
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: SensorlistitemBinding = SensorlistitemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var sensor = list[position]
        holder.binding.SensorName.text = sensor.name;
        holder.binding.SensorRange.text = "Maximum Range: " + sensor.maximumRange
    }

    override fun getItemCount(): Int {
        return list.size
    }


    class ViewHolder(binding: SensorlistitemBinding) : RecyclerView.ViewHolder(binding.root) {

         var binding: SensorlistitemBinding = binding

    }
}