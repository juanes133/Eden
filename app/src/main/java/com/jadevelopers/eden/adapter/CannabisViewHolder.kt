package com.jadevelopers.eden.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jadevelopers.eden.Cannabis
import com.jadevelopers.eden.databinding.TextRowItemBinding

class CannabisViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = TextRowItemBinding.bind(view)

    fun render(cannabisModel: Cannabis, onClickListener: (Cannabis) -> Unit) {
        binding.tvRealName.text = cannabisModel.realName
        binding.descripcionSabor.text = cannabisModel.sabor
        binding.descripcionEfecto.text = cannabisModel.efecto
        binding.descripcionThc.text= cannabisModel.thc
        binding.descripcionPrice.text = cannabisModel.price
        Glide.with(binding.ivCannabis.context).load(cannabisModel.photo).into(binding.ivCannabis)
        itemView.setOnClickListener {
            onClickListener(cannabisModel)
        }
    }
}