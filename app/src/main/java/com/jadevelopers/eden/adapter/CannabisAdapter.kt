package com.jadevelopers.eden.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jadevelopers.eden.model.Product
import com.jadevelopers.eden.R

class CannabisAdapter(
    private val CannabisList: List<Product>,
    private val onClickListener: (Product) -> Unit) : RecyclerView.Adapter<CannabisViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CannabisViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CannabisViewHolder(layoutInflater.inflate(R.layout.item_products, parent, false))
    }

    override fun onBindViewHolder(holder: CannabisViewHolder, position: Int) {
        holder.render(CannabisList[position], onClickListener)
    }

    override fun getItemCount(): Int = CannabisList.size
}