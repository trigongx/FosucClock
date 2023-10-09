package com.example.focusclock.presentation.fragments

import android.os.Bundle
import android.os.Handler
import android.view.View
import com.example.focusclock.core.base.BaseFragment
import com.example.focusclock.databinding.FragmentClockBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class ClockFragment : BaseFragment<FragmentClockBinding>() {

    private val handler = Handler()
    private lateinit var updateTimeRunnable: Runnable
    override fun inflateViewBinding(): FragmentClockBinding {
        return FragmentClockBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAndSetTime()
    }

    private fun initAndSetTime() {
        updateTimeRunnable = object:Runnable{
            override fun run() {
                val currentTime = Date()
                val calendar = Calendar.getInstance()
                calendar.time = currentTime

                val hours = calendar.get(Calendar.HOUR_OF_DAY)
                val minutes = calendar.get(Calendar.MINUTE)
                val seconds = calendar.get(Calendar.SECOND)
                val amPm = calendar.get(Calendar.AM_PM)

                if (amPm == Calendar.AM) binding.tvAmPm.visibility = View.VISIBLE

                binding.tvHours.text = hours.toString()
                binding.tvMinutes.text = minutes.toString()
                binding.tvSeconds.text = seconds.toString()

                handler.postDelayed(this,1000)
                }
            }
        handler.post(updateTimeRunnable)
    }



}