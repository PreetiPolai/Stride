package com.android.stride.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.stride.data.model.AccelerometerEntity
import com.android.stride.databinding.SensorlistitemBinding

class SensorDetailViewAdapter(val list : List<AccelerometerEntity>):
    RecyclerView.Adapter<SensorDetailViewAdapter.ViewHolder>() {


    class ViewHolder(binding: SensorlistitemBinding) : RecyclerView.ViewHolder(binding.root) {

        var binding: SensorlistitemBinding = binding

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SensorDetailViewAdapter.ViewHolder {
        val binding: SensorlistitemBinding = SensorlistitemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return SensorDetailViewAdapter.ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SensorDetailViewAdapter.ViewHolder, position: Int) {
        var sensor = list[position]
        holder.binding.SensorRange.text = sensor.totalAcc.toString();
    }

    override fun getItemCount(): Int {
        return list.size
    }

}