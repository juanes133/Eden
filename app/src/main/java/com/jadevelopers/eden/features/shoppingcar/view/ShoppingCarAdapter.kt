package com.jadevelopers.eden.features.shoppingcar.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jadevelopers.eden.R
import com.jadevelopers.eden.model.ShoppingCarItem

class ShoppingCarAdapter(
    private val shoppingCarList: ArrayList<ShoppingCarItem>,
    val context: Context
) : RecyclerView.Adapter<ShoppingCarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingCarViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ShoppingCarViewHolder(layoutInflater.inflate(R.layout.item_shopping_car, parent, false), context)
    }

    override fun onBindViewHolder(holder: ShoppingCarViewHolder, position: Int) {
        holder.render(shoppingCarList[position])
    }

    override fun getItemCount(): Int = shoppingCarList.size
}