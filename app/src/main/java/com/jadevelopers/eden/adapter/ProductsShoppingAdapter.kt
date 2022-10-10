package com.jadevelopers.eden.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jadevelopers.eden.R
import com.jadevelopers.eden.model.ProductsShopping

class ProductsShoppingAdapter(
    private val ProductsShoppingList: List<ProductsShopping>,
) : RecyclerView.Adapter<ProductsShoppingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsShoppingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ProductsShoppingViewHolder(
            layoutInflater.inflate(
                R.layout.item_products_shopping,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductsShoppingViewHolder, position: Int) {
        holder.render(ProductsShoppingList[position])
    }

    override fun getItemCount(): Int = ProductsShoppingList.size
}