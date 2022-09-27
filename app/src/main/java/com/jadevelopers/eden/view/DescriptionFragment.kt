package com.jadevelopers.eden.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.jadevelopers.eden.R
import com.jadevelopers.eden.databinding.FragmentDescriptionBinding

class DescriptionFragment : Fragment() {
private lateinit var binding: FragmentDescriptionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding= FragmentDescriptionBinding.inflate(inflater,container,false)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.titulo_descripcion)
        return binding.root
    }
}



