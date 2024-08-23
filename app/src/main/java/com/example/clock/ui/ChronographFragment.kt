package com.example.clock.ui

import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Chronometer
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.clock.R
import java.util.Locale
import java.util.logging.Handler

class ChronographFragment: Fragment() {
    private lateinit var timerText: TextView
    private lateinit var startButton: Button
    private lateinit var stopButton: Button
    private lateinit var resetButton: Button
    private var running = false
    private var startTime: Long = 0
    private var elapsedTime: Long = 0
    private var handler = android.os.Handler()
    private val updateTimer = object : Runnable {
        override fun run() {
            if (running) {
                elapsedTime = SystemClock.elapsedRealtime() - startTime
                val minutes = (elapsedTime / 60000).toInt()
                val seconds = ((elapsedTime % 60000) / 1000).toInt()
                val hundredths = ((elapsedTime % 1000) / 10).toInt()
                timerText.text = String.format(Locale.getDefault(), "%02d:%02d.%02d", minutes, seconds, hundredths)
                handler.postDelayed(this, 10)
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chronograph,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        timerText = view.findViewById(R.id.timer_text)
        startButton = view.findViewById(R.id.start_button)
        stopButton = view.findViewById(R.id.stop_button)
        resetButton = view.findViewById(R.id.reset_button)

        startButton.setOnClickListener {
            if (!running) {
                startTime = SystemClock.elapsedRealtime() - elapsedTime
                handler.post(updateTimer)
                running = true
            }
        }

        stopButton.setOnClickListener {
            if (running) {
                handler.removeCallbacks(updateTimer)
                elapsedTime = SystemClock.elapsedRealtime() - startTime
                running = false
            }
        }

        resetButton.setOnClickListener {
            handler.removeCallbacks(updateTimer)
            running = false
            startTime = 0
            elapsedTime = 0
            timerText.text = "00:00.00"
        }
        super.onViewCreated(view, savedInstanceState)
    }
}