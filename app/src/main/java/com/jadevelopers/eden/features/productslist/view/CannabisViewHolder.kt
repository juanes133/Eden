package com.jadevelopers.eden.features.productslist.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jadevelopers.eden.databinding.ItemProductsBinding
import com.jadevelopers.eden.model.Product

class CannabisViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemProductsBinding.bind(view)

    fun render(cannabisModel: Product, onClickListener: (Product) -> Unit) {
        binding.tvNamePlant.text = cannabisModel.namePlant
        binding.descriptionPrice.text = cannabisModel.price
        Glide.with(binding.ivCannabis.context).load(cannabisModel.photo).into(binding.ivCannabis)
        itemView.setOnClickListener {
            onClickListener(cannabisModel)
        }
    }
}