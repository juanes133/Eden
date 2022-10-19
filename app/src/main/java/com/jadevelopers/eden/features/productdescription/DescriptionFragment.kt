package com.jadevelopers.eden.features.productdescription

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.jadevelopers.eden.EdenApplication
import com.jadevelopers.eden.R
import com.jadevelopers.eden.databinding.FragmentDescriptionBinding
import com.jadevelopers.eden.model.Product
import com.jadevelopers.eden.features.productslist.viewmodel.ProductsViewModel
import com.jadevelopers.eden.features.shoppingcar.viewmodel.ShoppingCarViewModel
import com.jadevelopers.eden.features.shoppingcar.viewmodel.ShoppingCarViewModelFactory

class DescriptionFragment : Fragment(){
    private lateinit var binding: FragmentDescriptionBinding
    private val productsViewModel: ProductsViewModel by activityViewModels()
    private val shoppingCarViewModel: ShoppingCarViewModel by activityViewModels {
        ShoppingCarViewModelFactory((activity?.application as EdenApplication).shoppingCarRepository)
    }
    private val args: DescriptionFragmentArgs by navArgs()
    private var product: Product? = null
    private val grams = arrayOf("1", "2", "5", "10", "20", "50")
    private var amount = 1

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
        val txtAmount = "${getString(R.string.cantidad)}: $amount"
        binding.btnAmount.text = txtAmount
        Glide.with(binding.ivCannabis.context).load(product?.photo).into(binding.ivCannabis)

        binding.btnAdd.setOnClickListener {
            insertShoppingCarItem()
        }
        binding.btnAmount.setOnClickListener {
            activity?.let {

                val builder = AlertDialog.Builder(it)
                builder.setTitle(getString(R.string.cantidad))
                builder.setItems(grams) { _, which ->
                    val text = "${getString(R.string.cantidad)}: ${grams[which]}"
                    binding.btnAmount.text = text
                    amount = grams[which].toInt()
                    val operDos = binding.descriptionPrice.text
                    val result = amount * operDos.toString().toInt()
                    val textAdd = "${getString(R.string.Agregar)}: $result"
                    binding.btnAdd.text = textAdd
                }
                val dialog = builder.create()
                dialog.show()
            }
        }
        binding.btnShoppingCar.setOnClickListener {
            findNavController().navigate(R.id.shoppingFragment)
        }
        return binding.root
    }

    private fun insertShoppingCarItem() {
        context?.let {
            product?.let { product ->
                shoppingCarViewModel.insertShoppingCarItem(product.id.toInt(), amount)
            }
        }
    }

}



