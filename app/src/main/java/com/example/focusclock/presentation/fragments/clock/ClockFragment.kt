package com.example.focusclock.presentation.fragments.clock

import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.focusclock.R
import com.example.focusclock.core.base.BaseFragment
import com.example.focusclock.databinding.FragmentClockBinding

class ClockFragment : BaseFragment<FragmentClockBinding, ClockViewModel>() {
    override fun inflateViewBinding(): FragmentClockBinding =
        FragmentClockBinding.inflate(layoutInflater)

    override fun setViewModel(): ClockViewModel = ClockViewModel()

    override fun initView() {
        super.initView()
        viewModel.currentTime.observe(viewLifecycleOwner) { timeData ->
            binding.tvHours.text = timeData.hours
            binding.tvMinutes.text = timeData.minutes
            binding.tvMiniSeconds.text = timeData.seconds
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
        initMenuListener()
    }

    private fun initMenuListener() {
        binding.layoutMenu.btnFirst.setOnClickListener {
            findNavController().popBackStack()
            findNavController().navigate(R.id.stopwatchFragment)
        }
        binding.layoutMenu.btnSettings.setOnClickListener {
            Toast.makeText(requireContext(), "Soon", Toast.LENGTH_SHORT).show()
        }
        binding.layoutMenu.btnLast.setOnClickListener {
            findNavController().popBackStack()
            findNavController().navigate(R.id.pomodoroFragment)
        }
    }

}