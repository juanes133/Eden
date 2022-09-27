package com.jadevelopers.eden.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jadevelopers.eden.model.Product
import com.jadevelopers.eden.databinding.TextRowItemBinding

class CannabisViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = TextRowItemBinding.bind(view)

    fun render(cannabisModel: Product, onClickListener: (Product) -> Unit) {
        binding.tvNamePlant.text = cannabisModel.namePlant
        binding.descriptionTaste.text = cannabisModel.taste
        binding.descriptionEffect.text = cannabisModel.effect
        binding.descriptionThc.text= cannabisModel.thc
        binding.descriptionPrice.text = cannabisModel.price
        Glide.with(binding.ivCannabis.context).load(cannabisModel.photo).into(binding.ivCannabis)
        itemView.setOnClickListener {
            onClickListener(cannabisModel)
        }
    }
}