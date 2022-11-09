package com.jadevelopers.eden.features.checkout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.jadevelopers.eden.databinding.FragmentCheckOutBinding
import com.jadevelopers.eden.databinding.FragmentProductsBinding

class CheckOutFragment : Fragment() {
    private lateinit var binding: FragmentCheckOutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return binding.root
    }

}