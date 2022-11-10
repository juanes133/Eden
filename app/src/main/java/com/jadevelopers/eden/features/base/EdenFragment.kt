package com.jadevelopers.eden.features.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.jadevelopers.eden.EdenActivity

open class EdenFragment : Fragment() {

    internal lateinit var edenActivity: EdenActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        edenActivity = (activity as EdenActivity)
    }
}