package com.android.stride.ui.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.android.stride.data.model.AccelerometerEntity
import com.android.stride.data.repositories.AccelerometerRepo
import kotlinx.coroutines.launch

class AcceleratorViewModel(application: Application) : AndroidViewModel(application) {

    private val repo : AccelerometerRepo = AccelerometerRepo(application)

    private var current : MutableLiveData<String> = MutableLiveData();

    fun getList():LiveData<List<AccelerometerEntity>>{
        return repo.getActivityList()
    }

    fun insertTask(item: AccelerometerEntity) {
        viewModelScope.launch {
            repo.insertItem(item)
        }

        setDate(item.currentTime)
    }

    fun deleteTask(){
        viewModelScope.launch { repo.deleteAll()}
    }

    fun setDate(d : String){
        current.value = d
    }
}