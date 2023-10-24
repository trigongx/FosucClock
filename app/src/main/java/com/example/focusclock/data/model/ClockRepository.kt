package com.example.focusclock.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.Calendar
import java.util.Date
import java.util.Timer
import kotlin.concurrent.timerTask

class ClockRepository {
    private val _currentTime = MutableLiveData<ClockModel>()
    val currentTime: LiveData<ClockModel> get() = _currentTime

    private var timer: Timer? = null

    init {
        startUpdatingTime()
    }

    private fun startUpdatingTime() {
        timer = Timer()
        timer?.scheduleAtFixedRate(timerTask {
            updateCurrentTime()
        },0,1000)
    }

    private fun updateCurrentTime() {
        val currentTime = Date()
        val calendar = Calendar.getInstance()
        calendar.time = currentTime

        val hours = calendar.get(Calendar.HOUR_OF_DAY).toString()
        val minutes =
            if (calendar.get(Calendar.MINUTE) < 10) "0${calendar.get(Calendar.MINUTE)}" else calendar.get(
                Calendar.MINUTE
            ).toString()
        val seconds =
            if (calendar.get(Calendar.SECOND) < 10) "0${calendar.get(Calendar.SECOND)}" else calendar.get(
                Calendar.SECOND
            ).toString()

        _currentTime.postValue(ClockModel(hours, minutes, seconds))
    }

    fun stopUpdatingTime(){
        timer?.cancel()
    }
}