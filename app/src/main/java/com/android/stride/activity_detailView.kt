package com.android.stride

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.stride.databinding.ActivityDetailViewBinding

class activity_detailView : AppCompatActivity() {

    private var binding : ActivityDetailViewBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailViewBinding.inflate(layoutInflater);
        val view = binding?.root;
        setContentView(view)
    }

    private fun setAdapter() : Unit {

//        // assigning adapter to recycler view
//        val layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
//        binding.DetailsRecyclerView.layoutManager = layoutManager
//
//        val sensorListRecyclerViewAdapter = SensorListRecyclerViewAdapter(
//
//        )
//
//        binding.DetailsRecyclerView.adapter = sensorListRecyclerViewAdapter

    }

}