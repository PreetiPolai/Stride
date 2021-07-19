package com.android.stride.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android.stride.ui.fragments.accelerometer.AccelerometerFragment
import com.android.stride.ui.fragments.sensorList.SensorListFragment
import com.android.stride.ui.fragments.stepCouter.StepCountFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {

    private val NUM_TABS = 3
    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        when(position){
            0 -> return StepCountFragment()
            1 -> return  AccelerometerFragment()

        }

        return SensorListFragment()
    }
}