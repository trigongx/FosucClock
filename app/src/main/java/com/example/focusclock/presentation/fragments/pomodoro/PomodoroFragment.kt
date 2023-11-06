package com.example.focusclock.presentation.fragments.pomodoro

import android.view.View
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.focusclock.R
import com.example.focusclock.core.base.BaseFragment
import com.example.focusclock.databinding.FragmentPomodoroBinding
import kotlinx.coroutines.launch

class PomodoroFragment : BaseFragment<FragmentPomodoroBinding, PomodoroViewModel>() {

    override fun inflateViewBinding(): FragmentPomodoroBinding =
        FragmentPomodoroBinding.inflate(layoutInflater)

    override fun setViewModel(): PomodoroViewModel = PomodoroViewModel()

    override fun initView() {
        super.initView()
        binding.layoutMenu.img3Btn.setImageResource(R.drawable.ic_clock)
        viewModel.seconds.observe(viewLifecycleOwner){ seconds ->
            binding.tvSeconds.text = seconds.toString().padStart(2,'0')
        }
        viewModel.minutes.observe(viewLifecycleOwner){ minutes ->
            binding.tvMinutes.text = minutes.toString().padStart(2,'0')
        }
    }

    override fun initListener() {
        super.initListener()
        binding.mainContainer.setOnClickListener { binding.menuContainer.visibility = View.VISIBLE }
        binding.menuContainer.setOnClickListener { binding.menuContainer.visibility = View.GONE }
        binding.layoutMenu.btnFirst.setOnClickListener {
            findNavController().popBackStack()
            findNavController().navigate(R.id.stopwatchFragment)
        }
        binding.layoutMenu.btnLast.setOnClickListener {
            findNavController().popBackStack()
            findNavController().navigate(R.id.clockFragment)
        }
        binding.layoutMenu.btnSettings.setOnClickListener {
            Toast.makeText(requireContext(), "Soon", Toast.LENGTH_SHORT).show()
        }
        binding.btnPlayPause.setOnClickListener {
            viewModel.viewModelScope.launch { viewModel.toggleTimer() }
            if (viewModel.isTimerRunning.value == true){
                binding.btnPlayPause.setImageResource(R.drawable.ic_play)
            } else {
                binding.btnPlayPause.setImageResource(R.drawable.ic_pause)
            }
        }

    }

}