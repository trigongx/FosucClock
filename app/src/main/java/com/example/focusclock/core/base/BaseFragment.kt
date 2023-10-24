package com.example.focusclock.core.base

import android.content.Context
import android.os.Bundle
import android.os.PowerManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding,VM :ViewModel> : Fragment() {
    private var _binding: VB? = null
    protected val binding get() = _binding!!
    private var _viewModel:VM? = null
    protected val viewModel get() = _viewModel!!
    protected abstract fun inflateViewBinding(): VB
    protected abstract fun setViewModel():VM
    private lateinit var wakeLock: PowerManager.WakeLock

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = inflateViewBinding()
        _viewModel = setViewModel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        val powerManager = requireActivity().getSystemService(Context.POWER_SERVICE) as PowerManager
        wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "YourApp:WakeLockTag")

    }

    open fun initView() {}
    open fun initListener() {}

    override fun onResume() {
        super.onResume()
        wakeLock.acquire()
    }
    override fun onPause() {
        super.onPause()
        wakeLock.release()
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        _viewModel = null
    }
}