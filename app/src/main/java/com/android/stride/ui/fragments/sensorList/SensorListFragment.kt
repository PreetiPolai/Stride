package com.android.stride.ui.fragments.sensorList

import android.hardware.Sensor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.stride.ui.Sensor.SensorInitializer
import com.android.stride.ui.adapter.SensorListRecyclerViewAdapter
import com.android.stride.databinding.FragmentSensorListBinding


class SensorListFragment : Fragment() {

    private var sensorListBinding : FragmentSensorListBinding? = null
    private  lateinit var sensorInitializer : SensorInitializer

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = sensorListBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        sensorListBinding =  FragmentSensorListBinding.inflate(inflater, container, false)
        setAdapter()
        return sensorListBinding?.root
    }


    private fun setAdapter() : Unit {

        sensorInitializer = context?.let { SensorInitializer(it) }!!
        var list : List<Sensor> = sensorInitializer.getSensorList()


        // assigning adapter to recycler view
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = layoutManager

        val sensorListRecyclerViewAdapter = SensorListRecyclerViewAdapter(
            list
        )

        binding.recyclerView.adapter = sensorListRecyclerViewAdapter



    }

    override fun onDestroyView() {
        super.onDestroyView()
        sensorListBinding = null;
    }


}