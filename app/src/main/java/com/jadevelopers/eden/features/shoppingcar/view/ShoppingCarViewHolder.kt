package com.jadevelopers.eden.features.shoppingcar.view

import android.content.Context
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jadevelopers.eden.EdenActivity
import com.jadevelopers.eden.R
import com.jadevelopers.eden.databinding.ItemShoppingCarBinding
import com.jadevelopers.eden.model.ShoppingCarItem

class ShoppingCarViewHolder(view: View, val context: Context) : RecyclerView.ViewHolder(view) {

    private val binding = ItemShoppingCarBinding.bind(view)
    private var gr = "gr"
    private val grams = arrayOf("1", "2", "5", "10", "20", "50")

    fun render(shoppingCarModel: ShoppingCarItem) {
        binding.tvNamePlant.text = shoppingCarModel.namePlant
        binding.descriptionPrice.text = shoppingCarModel.price
        Glide.with(binding.ivCannabis.context).load(shoppingCarModel.photo)
            .into(binding.ivCannabis)
        binding.btnDelete.setOnClickListener {
            (context as EdenActivity).shoppingCarViewModel.deleteShoppingCarItem(shoppingCarModel.id)
            binding.itemShopping.isVisible = false
        }

        val result = shoppingCarModel.amount * shoppingCarModel.price.toInt()
        val total = "${context.getString(R.string.total)}: $result"
        val txtAmount =
            "${context.getString(R.string.cantidad)}: ${shoppingCarModel.amount}/$gr"
        binding.tvTotal.text = total
        binding.btnAmount.text = txtAmount
        binding.btnAmount.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle(context.getString(R.string.cantidad))
            builder.setItems(grams) { _, dialog ->
                val text = "${context.getString(R.string.cantidad)}: ${grams[dialog]}/$gr"
                binding.btnAmount.text = text
                val amount = grams[dialog].toInt()
                (context as EdenActivity).shoppingCarViewModel.insertShoppingCarItem(
                    shoppingCarModel.id,
                    amount)
                val result = amount * shoppingCarModel.price.toInt()
                val total = "${context.getString(R.string.total)}: $result"
                binding.tvTotal.text = total
            }
            val dialog = builder.create()
            dialog.show()
        }
    }
}
        

