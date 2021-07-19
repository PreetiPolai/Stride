package com.android.stride.ui.fragments.stepCouter

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.android.stride.ui.Sensor.SensorInitializer
import com.android.stride.data.model.StepCountEntity
import com.android.stride.databinding.FragmentStepCountBinding
import com.android.stride.ui.viewModel.StepCountViewModel
import java.text.SimpleDateFormat
import java.util.*


class StepCountFragment : Fragment() , SensorEventListener{

    private var stepCountBinding: FragmentStepCountBinding? = null
    private  lateinit var sensorInitializer : SensorInitializer
    private var step: Int? = null
    var s = 0
    private lateinit var vm : StepCountViewModel
    private lateinit var formattedDate : String

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = stepCountBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        setSensor()
        super.onCreate(savedInstanceState)
        vm = ViewModelProviders.of(this).get(StepCountViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        stepCountBinding = FragmentStepCountBinding.inflate(inflater, container, false)
        binding.stepCount.text = "0"
        return stepCountBinding?.root;

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val c: Date = Calendar.getInstance().getTime()
        val df = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
         formattedDate = df.format(c)
         vm.setDate(formattedDate)

        vm.Today?.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            step = it.stepCount
            binding.vmStep.text = "VM :" + step
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        stepCountBinding = null;
        sensorInitializer.unregisterListener(this)
    }


    private fun setSensor() : Unit {

        sensorInitializer = context?.let { SensorInitializer(it) }!!

        sensorInitializer.setSensor(Sensor.TYPE_STEP_DETECTOR)

    }

    override fun onSensorChanged(p0: SensorEvent?) {


            if (p0 != null) {
                Log.d("STEP COUNT", "value :" + p0.values.get(0))


                s = s+1

                step?.let { StepCountEntity(formattedDate, it) }?.let { vm.setSteps(it) }

                binding.stepCount.text = s.toString()  + "and vm" + step.toString()

            }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        Toast.makeText(context,"Accuracy has changed",Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        sensorInitializer?.registerListener(this)
    }
}