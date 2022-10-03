package com.jadevelopers.eden.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.jadevelopers.eden.R
import com.jadevelopers.eden.databinding.FragmentDescriptionBinding
import com.jadevelopers.eden.model.Product
import com.jadevelopers.eden.viewmodel.ProductsViewModel

class DescriptionFragment : Fragment() {
    private lateinit var binding: FragmentDescriptionBinding
    private val productsViewModel: ProductsViewModel by activityViewModels()
    private val args: DescriptionFragmentArgs by navArgs()
    private var product: Product? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDescriptionBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.titulo_descripcion)

        product = productsViewModel.productsList.value?.firstOrNull { x -> x.id == args.idProduct }
        binding.tvNamePlant.text = product?.namePlant
        binding.descriptionProduct.text = product?.description
        binding.descriptionThc.text = product?.thc
        binding.descriptionEffect.text = product?.effect
        binding.descriptionTaste.text = product?.taste
        binding.descriptionPrice.text = product?.price
        Glide.with(binding.ivCannabis.context).load(product?.photo).into(binding.ivCannabis)

        val spinner: Spinner = binding.spinner
        context?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.cuanty_gr,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapter
            }
        }
        return binding.root
    }
}


