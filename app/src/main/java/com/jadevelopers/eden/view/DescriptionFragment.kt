package com.jadevelopers.eden.view

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.icu.text.CaseMap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.jadevelopers.eden.R
import com.jadevelopers.eden.databinding.FragmentDescriptionBinding
import com.jadevelopers.eden.model.Product
import com.jadevelopers.eden.viewmodel.ProductsViewModel
import java.util.stream.DoubleStream.builder

class DescriptionFragment : Fragment() {
    private lateinit var binding: FragmentDescriptionBinding
    private val productsViewModel: ProductsViewModel by activityViewModels()
    private val args: DescriptionFragmentArgs by navArgs()
    private var product: Product? = null
    val gramos = arrayOf("1", "2", "5", "10", "20", "50")


    @SuppressLint("SetTextI18n")
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
        binding.btnAmount.setOnClickListener {
            activity?.let {
                val builder = AlertDialog.Builder(it)
                builder.setTitle(getString(R.string.Cantidad))
                builder.setItems(gramos) { _, which ->
                        binding.btnAmount.text = "${getString(R.string.Cantidad)}${gramos[which]}"
                }
                val dialog = builder.create()
             dialog.show()
            }
        }
        Glide.with(binding.ivCannabis.context).load(product?.photo).into(binding.ivCannabis)
        return binding.root
    }

}



