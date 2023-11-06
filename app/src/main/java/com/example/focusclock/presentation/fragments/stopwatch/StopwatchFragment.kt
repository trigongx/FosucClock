package com.example.focusclock.presentation.fragments.stopwatch

import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.focusclock.R
import com.example.focusclock.core.base.BaseFragment
import com.example.focusclock.databinding.FragmentStopwatchBinding
import kotlinx.coroutines.launch

class StopwatchFragment : BaseFragment<FragmentStopwatchBinding, StopwatchViewModel>() {
    override fun inflateViewBinding(): FragmentStopwatchBinding =
        FragmentStopwatchBinding.inflate(layoutInflater)

    override fun setViewModel(): StopwatchViewModel = StopwatchViewModel()

    override fun initView() {
        super.initView()
        binding.layoutMenu.img1Btn.setImageResource(R.drawable.ic_clock)
        viewModel.currentTime.observe(viewLifecycleOwner) { time ->
            val (hours, minutes, seconds) = formatTime(time)
            binding.tvHours.text = hours
            binding.tvMinutes.text = minutes
            binding.tvSeconds.text = seconds
        }
    }

    override fun initListener() {
        super.initListener()
        binding.mainContainer.setOnClickListener {
            binding.menuContainer.visibility = View.VISIBLE
        }
        binding.menuContainer.setOnClickListener {
            binding.menuContainer.visibility = View.GONE
        }
        binding.layoutMenu.btnSettings.setOnClickListener {
            Toast.makeText(requireContext(), "Soon", Toast.LENGTH_SHORT).show()
        }
        binding.layoutMenu.btnLast.setOnClickListener {
            findNavController().popBackStack()
            findNavController().navigate(R.id.pomodoroFragment)
        }
        binding.layoutMenu.btnFirst.setOnClickListener {
            findNavController().popBackStack()
            findNavController().navigate(R.id.clockFragment)
        }
        binding.btnPlayPause.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch { viewModel.toggleStartPause() }
            if (viewModel.isRunning.value == true){
                binding.btnPlayPause.setImageResource(R.drawable.ic_play)
                binding.btnReset.visibility = View.VISIBLE
            } else {
                binding.btnPlayPause.setImageResource(R.drawable.ic_pause)
                binding.btnReset.visibility = View.GONE
            }

        }
        binding.btnReset.setOnClickListener {
            viewModel.reset()
            binding.btnPlayPause.setImageResource(R.drawable.ic_play)
            binding.btnReset.visibility = View.GONE
        }
    }

    private fun formatTime(timeInMillis: Long): Triple<String, String, String> {
        val hours = (timeInMillis / 3600000).toString().padStart(2, '0')
        val minutes = ((timeInMillis % 3600000) / 60000).toString().padStart(2, '0')
        val seconds = ((timeInMillis % 60000) / 1000).toString().padStart(2, '0')
        return Triple(hours, minutes, seconds)
    }
}