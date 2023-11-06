package com.example.focusclock.presentation.fragments.pomodoro

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PomodoroViewModel:ViewModel() {
    private val _minutes = MutableLiveData<Long>()
    val minutes = _minutes
    private val _seconds = MutableLiveData<Long>()
    val seconds = _seconds
    private var countDownTimer: CountDownTimer? = null
    private val _isTimerRunning = MutableLiveData<Boolean>()
    val isTimerRunning = _isTimerRunning

    init {
        _minutes.value = 25
        _seconds.value = 0
    }

    suspend fun toggleTimer(){
        if (_isTimerRunning.value == true){
            stopTimer()
        }else{
            startTimer()
        }
    }

    private fun startTimer() {
        countDownTimer = object : CountDownTimer((_minutes.value!! * 60 * 1000 + _seconds.value!! * 1000),1000){
            override fun onTick(millisUntilFinished: Long) {
                val minutesLeft = millisUntilFinished / 1000 / 60
                val secondsLeft = millisUntilFinished / 1000 % 60

                _minutes.postValue(minutesLeft)
                _seconds.postValue(secondsLeft)
            }

            override fun onFinish() {
                _minutes.postValue(0)
                _seconds.postValue(0)
                _isTimerRunning.postValue(false)
            }
        }
        countDownTimer?.start()
        _isTimerRunning.postValue(true)
    }

    private fun stopTimer() {
        countDownTimer?.cancel()
        _isTimerRunning.postValue(false)
    }

}