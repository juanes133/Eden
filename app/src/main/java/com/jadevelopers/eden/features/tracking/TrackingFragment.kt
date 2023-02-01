package com.jadevelopers.eden.features.tracking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import com.jadevelopers.eden.databinding.FragmentTrackingBinding
import com.jadevelopers.eden.features.base.EdenFragment

class TrackingFragment : EdenFragment() {
    private lateinit var binding: FragmentTrackingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentTrackingBinding.inflate(inflater, container, false)
        edenActivity.menu?.children?.first()?.isVisible = false
        return binding.root
    }
}