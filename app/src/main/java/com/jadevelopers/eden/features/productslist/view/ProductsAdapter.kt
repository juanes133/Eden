package com.jadevelopers.eden.features.productslist.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jadevelopers.eden.model.Product
import com.jadevelopers.eden.R

class ProductsAdapter(
    private val productList: List<Product>,
    private val onClickListener: (Product) -> Unit) : RecyclerView.Adapter<ProductsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ProductsViewHolder(layoutInflater.inflate(R.layout.item_products, parent, false))
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.render(productList[position], onClickListener)
    }

    override fun getItemCount(): Int = productList.size
}