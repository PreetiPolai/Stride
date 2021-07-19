package com.android.stride.ui.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.android.stride.data.model.AccelerometerEntity
import com.android.stride.data.model.StepCountEntity
import com.android.stride.data.repositories.StepCounterRepo
import kotlinx.coroutines.launch

class StepCountViewModel(application: Application) : AndroidViewModel(application) {

    private val repo : StepCounterRepo = StepCounterRepo(application)
    private val date = MutableLiveData<String>()

    //keep the step count
    private var _steps : MutableLiveData<Int> = MutableLiveData();

    //if there's change in steps get the step
    val Today : LiveData<StepCountEntity>? = this.date.value?.let { repo.getStepToday(it) }


    val steps : LiveData<Int>
        get() = _steps


    fun setSteps(item: StepCountEntity){
        if(Today != null){
            //whenthe data is being entered for the first time
             date.value = item.date
             insert(item).toString()
            _steps.value = 1

        }
        else {
            update(item)
            _steps.value = item.stepCount
        }
    }

    fun insert(item : StepCountEntity){
        viewModelScope.launch {
             repo.insertItem(item)
        }
    }

    fun update(item : StepCountEntity){
        viewModelScope.launch {
            repo.update(item)
        }
    }

    fun setDate(date : String){
        this.date.value = date
    }
}