package com.example.focusclock.presentation.fragments.stopwatch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.focusclock.data.model.StopwatchModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask

class StopwatchViewModel : ViewModel() {
    private val model = StopwatchModel()
    private var timer: Timer? = null
    private val _currentTime = MutableLiveData<Long>()
    private val _isRunning = MutableLiveData<Boolean>()
    val currentTime = _currentTime
    val isRunning = _isRunning

    init {
        _isRunning.value = false
    }

    suspend fun toggleStartPause() {
        viewModelScope.launch(Dispatchers.Default) {
            if (_isRunning.value == true) {
                model.stop()
                timer?.cancel()
                timer = null
                _isRunning.postValue(false)
            } else {
                model.start()
                timer = Timer().apply {
                    scheduleAtFixedRate(object : TimerTask() {
                        override fun run() {
                            _currentTime.postValue(model.getTime())
                        }
                    }, 0, 1000)
                }
                _isRunning.postValue(true)
            }
        }
    }

    fun reset(){
        model.reset()
        _currentTime.postValue(0)
        _isRunning.postValue(false)
    }

}