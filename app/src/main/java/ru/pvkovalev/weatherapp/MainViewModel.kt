package ru.pvkovalev.weatherapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.pvkovalev.weatherapp.adapters.WeatherModel


class MainViewModel : ViewModel() {
    val liveDataCurrent = MutableLiveData<WeatherModel>()
    val liveDataList = MutableLiveData<List<WeatherModel>>()
}