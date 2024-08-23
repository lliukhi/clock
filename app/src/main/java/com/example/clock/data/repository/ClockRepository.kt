package com.example.clock.data.repository

import android.icu.text.SimpleDateFormat
import android.icu.util.TimeZone
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Choreographer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.clock.data.model.Clock
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.lang.System.currentTimeMillis
import java.util.Date
import java.util.Locale
import kotlin.coroutines.CoroutineContext


object  ClockRepository {

    private val _clockData = MutableLiveData<List<Clock>>()
    val clockData: LiveData<List<Clock>> get() = _clockData
    private var handler: Handler? = null
    private var runnable: Runnable? = null
    private val timeZones = listOf("Asia/Shanghai", "America/New_York", "Europe/London","Asia/Shanghai", "America/New_York", "Europe/London","Asia/Shanghai", "America/New_York", "Europe/London","Asia/Shanghai", "America/New_York", "Europe/London")
    private const val TAG = "ClockRepository"
    fun searchClock(): LiveData<List<Clock>> {

        stopUpdatingClock() // Ensure no multiple jobs running
        handler = Handler(Looper.getMainLooper())
        runnable = object : Runnable {
            override fun run() {
                val clocks = timeZones.map { Clock(getCurrentTime(it),it) }
                _clockData.value = clocks
                handler?.postDelayed(this, 10) // Update every 10 milliseconds
            }
        }
        handler?.post(runnable!!)

        return _clockData
    }

    private fun getCurrentTime(timeZone: String): String {

        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone(timeZone)

        return sdf.format(System.currentTimeMillis())

    }

    fun stopUpdatingClock() {
        handler?.removeCallbacks(runnable!!)
        handler = null
        runnable = null
    }
    fun getTimeDifference(timeZone1: String, timeZone2: String): Long {
        val now = Date()
        val tz1 = TimeZone.getTimeZone(timeZone1)
        val tz2 = TimeZone.getTimeZone(timeZone2)

        val offset1 = tz1.getOffset(now.time).toLong()
        val offset2 = tz2.getOffset(now.time).toLong()

        return (offset2 - offset1) / (1000 * 60 * 60) // 返回小时差
    }
}