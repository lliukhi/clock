package com.example.clock.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.clock.data.model.Clock
import com.example.clock.data.repository.ClockRepository


class ClockViewModel : ViewModel() {
    private val clockData = MutableLiveData<String>()
    var clockList = ArrayList<Clock>()
    val clockLiveData :LiveData<List<Clock>> = clockData.switchMap {
        ClockRepository.searchClock()

    }
    fun updateClock(timeZone: String) {
        clockData.value = timeZone
    }
}