package com.example.focusclock.presentation.fragments.clock

import com.example.focusclock.core.base.BaseFragment
import com.example.focusclock.databinding.FragmentClockBinding

class ClockFragment : BaseFragment<FragmentClockBinding,ClockViewModel>() {
    override fun inflateViewBinding(): FragmentClockBinding = FragmentClockBinding.inflate(layoutInflater)
    override fun setViewModel(): ClockViewModel = ClockViewModel()

    override fun initView() {
        super.initView()
        viewModel.currentTime.observe(viewLifecycleOwner){timeData->
            binding.tvHours.text = timeData.hours
            binding.tvMinutes.text = timeData.minutes
            binding.tvSeconds.text = timeData.seconds
        }
    }

    override fun initListener() {
        super.initListener()
        binding.mainContainer.setOnClickListener {
            //showAdditionalMenu()
        }
    }

    /*private fun showAdditionalMenu() {
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setView(R.layout.layout_extra_menu).show()
    }*/
    /*override fun onDestroy() {
        super.onDestroy()
        timer?.cancel()
    }*/

}