package com.jadevelopers.eden.view

import android.os.Bundle
import android.view.*
import androidx.activity.addCallback
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.jadevelopers.eden.R
import com.jadevelopers.eden.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), MenuProvider {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.btnproducts.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_productsFragment2)
        }
        binding.btntrackin.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_trackingFragment)
        }
        activity?.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
       //activity?.apply { onBackPressedDispatcher.addCallback(this){
            // Pendiente lineas de cerrar sesion//
        //} }
        return binding.root
    }


    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.itemId == R.id.signout) {
            Firebase.auth.signOut()
            activity?.onBackPressed()
        }
        return false
    }
}