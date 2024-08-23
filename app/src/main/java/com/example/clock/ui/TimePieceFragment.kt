package com.example.clock.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.NumberPicker
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

import androidx.fragment.app.Fragment
import com.example.clock.R
import java.util.Locale
import java.util.logging.Handler

class TimePieceFragment :Fragment() {
    private lateinit var timerText: TextView
    private lateinit var startButton: Button
    private lateinit var stopButton: Button
    private lateinit var resetButton: Button
    private lateinit var hourPicker: NumberPicker
    private lateinit var minutePicker: NumberPicker
    private lateinit var secondPicker: NumberPicker
    private lateinit var timePickerLayout: LinearLayout
    private lateinit var setRingtoneButton: Button
    private var running = false
    private var startTime: Long = 0
    private var elapsedTime: Long = 0
    private var countdownTime: Long = 0
    private var handler = android.os.Handler()
    private var ringtoneUri: Uri? = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
    private var ringtone: Ringtone? = null
    private val updateTimer = object : Runnable {
        override fun run() {
            if (running) {
                elapsedTime = SystemClock.elapsedRealtime() - startTime
                val remainingTime = countdownTime - elapsedTime
                if (remainingTime > 0) {
                    val hours = (remainingTime / 3600000).toInt()
                    val minutes = ((remainingTime % 3600000) / 60000).toInt()
                    val seconds = ((remainingTime % 60000) / 1000).toInt()
                    timerText.text = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds)
                    handler.postDelayed(this, 1000)
                } else {
                    timerText.text = "00:00:00"
                    timePickerLayout.visibility = View.VISIBLE
                    timerText.visibility = View.GONE
                    running = false
                    ringtone?.play()
                }
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_timepiece,container,false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        timerText = view.findViewById(R.id.timer_text)
        startButton = view.findViewById(R.id.start_button)
        setRingtoneButton = view.findViewById(R.id.set_ringtone_button)
        stopButton = view.findViewById(R.id.stop_button)
        resetButton = view.findViewById(R.id.reset_button)
        hourPicker = view.findViewById(R.id.hour_picker)
        minutePicker = view.findViewById(R.id.minute_picker)
        secondPicker = view.findViewById(R.id.second_picker)
        timePickerLayout = view.findViewById(R.id.time_picker_layout)

        // 动态设置 minValue 和 maxValue
        hourPicker.minValue = 0
        hourPicker.maxValue = 23
        setNumberPickerTextColor(hourPicker, Color.WHITE)

        minutePicker.minValue = 0
        minutePicker.maxValue = 59
        setNumberPickerTextColor(minutePicker, Color.WHITE)

        secondPicker.minValue = 0
        secondPicker.maxValue = 59
        setNumberPickerTextColor(secondPicker, Color.WHITE)

        // 设置显示值
        hourPicker.displayedValues = Array(24) { "$it 小时" }
        minutePicker.displayedValues = Array(60) { "$it 分钟" }
        secondPicker.displayedValues = Array(60) { "$it 秒" }
        ringtone = RingtoneManager.getRingtone(activity, ringtoneUri)
        startButton.setOnClickListener {
            if (!running) {
                val hours = hourPicker.value
                val minutes = minutePicker.value
                val seconds = secondPicker.value
                countdownTime = (hours * 3600 + minutes * 60 + seconds) * 1000L
                startTime = SystemClock.elapsedRealtime()
                handler.post(updateTimer)
                running = true
                timePickerLayout.visibility = View.GONE
                timerText.visibility = View.VISIBLE
            }
        }

        stopButton.setOnClickListener {
            if (running) {
                handler.removeCallbacks(updateTimer)
                countdownTime -= SystemClock.elapsedRealtime() - startTime
                running = false
            }
        }

        resetButton.setOnClickListener {
            handler.removeCallbacks(updateTimer)
            running = false
            countdownTime = 0
            timerText.text = "00:00:00"
            timePickerLayout.visibility = View.VISIBLE
            timerText.visibility = View.GONE
        }
        setRingtoneButton.setOnClickListener {
            val intent = Intent(RingtoneManager.ACTION_RINGTONE_PICKER)
            intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_ALARM)
            intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "Select Alarm Sound")
            intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, ringtoneUri)
            startActivityForResult(intent, 999)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 999 && resultCode == Activity.RESULT_OK) {
            ringtoneUri = data?.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI)
            ringtone = RingtoneManager.getRingtone(activity, ringtoneUri)
        }
}
        @SuppressLint("SoonBlockedPrivateApi")
        private fun setNumberPickerTextColor(numberPicker: NumberPicker, color: Int) {
            for (i in 0 until numberPicker.childCount) {
                val child = numberPicker.getChildAt(i)
                if (child is EditText) {
                    try {
                        child.setTextColor(color)
                        numberPicker.invalidate()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
    }

}