package com.android.stride.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.android.stride.ui.adapter.ViewPagerAdapter
import com.android.stride.databinding.ActivityHomeBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewPager : ViewPager2
    private lateinit var tabLayout : TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater);
        val view = binding.root;
        setContentView(view)

        val a = arrayOf("Steps","Speed","Sensors")

        //instantiation of the view pager and the tab layout
        viewPager = binding.viewPager
        tabLayout = binding.tabLayout

        //attach adapter to the viewPager
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager,lifecycle)
        viewPager.adapter = viewPagerAdapter

        TabLayoutMediator(tabLayout,viewPager){ tab: TabLayout.Tab, i: Int ->

            tab.text = a[i]

        }.attach()

    }




}