package com.android.stride.ui.fragments.accelerometer

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.android.stride.ui.Sensor.SensorInitializer
import com.android.stride.data.model.AccelerometerEntity
import com.android.stride.databinding.FragmentAccelerometerBinding
import com.android.stride.ui.viewModel.AcceleratorViewModel
import com.android.stride.ui.filesUtils.*
import com.github.doyaaaaaken.kotlincsv.dsl.csvWriter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class AccelerometerFragment : Fragment(), SensorEventListener {

    private var accelerometerBinding: FragmentAccelerometerBinding? = null
    private lateinit var sensorInitializerAccelerometer: SensorInitializer
    private lateinit var sensorInitializerGyroscope: SensorInitializer
    private lateinit var sensorInitializerMagnetometer: SensorInitializer
    private lateinit var avm : AcceleratorViewModel
    private var Sensordata : List<AccelerometerEntity>? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = accelerometerBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        avm = ViewModelProviders.of(this).get(AcceleratorViewModel::class.java)

    }


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        accelerometerBinding = FragmentAccelerometerBinding.inflate(inflater, container, false)
        return accelerometerBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //set the sensors
        setSensor()

        //set on click listeners
        onclickListeners()

        //get list of values
        setSensorData()
    }

    private fun setSensor(): Unit {

        sensorInitializerAccelerometer = context?.let { SensorInitializer(it) }!!
        sensorInitializerMagnetometer = context?.let { SensorInitializer(it) }!!
        sensorInitializerGyroscope = context?.let { SensorInitializer(it) }!!

        sensorInitializerAccelerometer.setSensor(Sensor.TYPE_LINEAR_ACCELERATION)
        sensorInitializerGyroscope.setSensor(Sensor.TYPE_GYROSCOPE)
        sensorInitializerMagnetometer.setSensor(Sensor.TYPE_MAGNETIC_FIELD)

    }

    override fun onSensorChanged(p0: SensorEvent?) {
        if (p0 != null) {

            var acc : Double = 0.0
            var mag : Double = 0.0
            var gyro : Double = 0.0

            val c: Date = Calendar.getInstance().getTime()

            val df = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
            val formattedDate: String = df.format(c)

            if(p0.sensor.type == Sensor.TYPE_GYROSCOPE){
                   gyro =  getGyrocope(p0)
                //to insert current data
                val accelerometerEntity =  AccelerometerEntity(formattedDate,acc,mag,gyro,p0.timestamp,c.toString())
                avm.insertTask(accelerometerEntity)
                }

            if(p0.sensor.type == Sensor.TYPE_LINEAR_ACCELERATION) {
                acc = setAccelerometer(p0)
                //to insert current data
                val accelerometerEntity =  AccelerometerEntity(formattedDate,acc,mag,gyro,p0.timestamp,c.toString())
                avm.insertTask(accelerometerEntity)
            }

            if (p0.sensor.type == Sensor.TYPE_MAGNETIC_FIELD)
                {
                    mag = getMagnetometer(p0)
                    //to insert current data
                    val accelerometerEntity =  AccelerometerEntity(formattedDate,acc,mag,gyro,p0.timestamp,c.toString())
                    avm.insertTask(accelerometerEntity)
                }

           }
        }


    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        Toast.makeText(context, "Accuracy has changed", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        accelerometerBinding = null;
    }

    override fun onResume() {
        super.onResume()
    }


    fun onclickListeners(){

        binding.StartRecording.setOnClickListener {
            if(binding.StartRecording.text == "START"){
                sensorInitializerAccelerometer?.registerListener(this)
                sensorInitializerGyroscope?.registerListener(this)
                sensorInitializerMagnetometer?.registerListener(this)
                binding.StartRecording.text = "STOP"
            }
            else {
                sensorInitializerMagnetometer.unregisterListener(this)
                sensorInitializerAccelerometer.unregisterListener(this)
                sensorInitializerGyroscope.unregisterListener(this)
                binding.StartRecording.text = "START"


                 cSVGeneration()
                 avm.deleteTask()




            }
        }
    }

    private fun setAccelerometer(p0: SensorEvent) : Double{
        var x = p0.values[0]
        var y = p0.values[1]
        var z = p0.values[2]

        var total = Math.sqrt(Math.pow(x.toDouble(), 2.0) + Math.pow(y.toDouble(), 2.0) + Math.pow(z.toDouble(), 2.0))

        //display the data on screen
        binding.AccelerometerValue.visibility = View.VISIBLE
        binding.AccelerometerValue.text = total.toString()

        return total
    }

    private fun getGyrocope(p0: SensorEvent) : Double {

        var x = p0.values[0]
        var y = p0.values[1]
        var z = p0.values[2]

        var total = Math.sqrt(Math.pow(x.toDouble(), 2.0) + Math.pow(y.toDouble(), 2.0) + Math.pow(z.toDouble(), 2.0))

        //display data on screen
        binding.GyroscopeValue.visibility = View.VISIBLE
        binding.GyroscopeValue.text = total.toString()

        return total
    }

    private fun getMagnetometer(p0: SensorEvent) : Double{
        var x = p0.values[0]
        var y = p0.values[1]
        var z = p0.values[2]

        var total = Math.sqrt(Math.pow(x.toDouble(), 2.0) + Math.pow(y.toDouble(), 2.0) + Math.pow(z.toDouble(), 2.0))

        //delete all tasks
        //mvm.deleteTask()

        //display data on screen
        binding.MagnetometerValue.visibility = View.VISIBLE
        binding.MagnetometerValue.text = total.toString()

        return total;
    }

    private fun setSensorData(){

        avm.getList().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            Sensordata = it
        })
    }

     fun cSVGeneration(){

        val s = "Activity" +  Math.random() + ".csv"

        var csvFile = context?.let { generateFile(it,s) }
        if(csvFile!=null){

            Toast.makeText(context,".CSV file generated successfully !",Toast.LENGTH_SHORT).show()

            GlobalScope.launch {
                withContext(Dispatchers.IO){exportSensorData(csvFile)}
            }


            val intent = context?.let { goToFileIntent(it,csvFile) }
            startActivity(intent)

        }

        else{
            Toast.makeText(context,".CSV file not generated successfully !",Toast.LENGTH_SHORT).show()
        }

    }



     fun exportSensorData(csvFile: File) {

        csvWriter().open(csvFile,append = false) {
            //header
           writeRow(listOf( "[Date]", "[totalAcc]","[totalMag]", "[totalGyro]","[time]"))
            Sensordata?.forEach {
                writeRow(listOf(it.date,it.totalAcc,it.totalMag,it.totalGyro,it.currentTime))
            }
        }
    }

}
