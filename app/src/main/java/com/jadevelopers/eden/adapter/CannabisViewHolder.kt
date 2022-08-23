package com.jadevelopers.eden.adapter

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jadevelopers.eden.Cannabis
import com.jadevelopers.eden.databinding.FragmentProductsBinding

class CannabisViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val binding = FragmentProductsBinding.bind(view)

    fun render(cannabisModel: Cannabis, onClickListener: (Cannabis) -> Unit) {
        binding.tvRealName.text = cannabisModel.realName
        binding.tvDescription.text = cannabisModel.description
        binding.tvPrice.text = cannabisModel.price
        Glide.with(binding.ivCannabis.context).load(cannabisModel.photo).into(binding.ivCannabis)
        itemView.setOnClickListener {
            onClickListener(cannabisModel)
        }
    }
}