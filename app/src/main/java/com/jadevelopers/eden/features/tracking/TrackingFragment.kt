package com.jadevelopers.eden.features.tracking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.fragment.app.Fragment
import com.jadevelopers.eden.EdenActivity
import com.jadevelopers.eden.databinding.FragmentTrackingBinding

class TrackingFragment : Fragment() {
    private lateinit var binding: FragmentTrackingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTrackingBinding.inflate(inflater, container, false)
        (activity as EdenActivity).menu?.children?.first()?.isVisible = false
        return binding.root
    }
}