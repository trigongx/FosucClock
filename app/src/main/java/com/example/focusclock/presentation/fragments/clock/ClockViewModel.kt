package com.example.focusclock.presentation.fragments.clock

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.focusclock.data.model.ClockModel
import com.example.focusclock.data.model.ClockRepository

class ClockViewModel: ViewModel() {
    private val repository = ClockRepository()
    val currentTime:LiveData<ClockModel> get() = repository.currentTime

    override fun onCleared() {
        super.onCleared()
        repository.stopUpdatingTime()
    }
}