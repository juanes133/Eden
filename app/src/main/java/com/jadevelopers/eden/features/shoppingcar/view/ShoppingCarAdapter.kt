package com.jadevelopers.eden.features.shoppingcar.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jadevelopers.eden.R
import com.jadevelopers.eden.features.productslist.view.ProductsViewHolder
import com.jadevelopers.eden.model.Product
import com.jadevelopers.eden.model.ShoppingCarItem

class ShoppingCarAdapter(
    private val ShoppingCarList: ArrayList<ShoppingCarItem>,
) : RecyclerView.Adapter<ShoppingCarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingCarViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ShoppingCarViewHolder(layoutInflater.inflate(R.layout.item_shopping_car, parent, false))
    }

    override fun onBindViewHolder(holder: ShoppingCarViewHolder, position: Int) {
        holder.render(ShoppingCarList[position])
    }

    override fun getItemCount(): Int = ShoppingCarList.size
}